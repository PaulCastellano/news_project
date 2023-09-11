package com.example.domain.usacase.content

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.model.dto.NewContentDto
import com.example.model.dto.toNewContentDtoList
import com.example.repository.news.ContentRepository
import java.io.IOException

class ContentPagingSource(
    private val repository: ContentRepository,
    private val options: Map<String, String>
): PagingSource<Int, NewContentDto>() {
    override fun getRefreshKey(state: PagingState<Int, NewContentDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewContentDto> {
        // for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {
            val response = repository.getContentList(page, options)
            val newsList = response.paginationInfo.results.orEmpty().toNewContentDtoList()
            val result = newsList.map {
                val NewsFav = repository.getReadLater(it.id)
                it.isReadLaterElement = NewsFav != null
                it
            }

            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            // IOException for network failures.
            return LoadResult.Error(exception)
        }
    }
}