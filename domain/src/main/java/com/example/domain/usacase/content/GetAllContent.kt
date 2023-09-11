package com.example.domain.usacase.content

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.framework.core.usecase.FlowPagingUseCase
import com.example.model.dto.NewContentDto
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllContent
@Inject constructor(
    private val repository: ContentRepository
): FlowPagingUseCase<GetAllContent.Params, NewContentDto>() {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    )

    override fun execute(params: Params): Flow<PagingData<NewContentDto>> {
        return Pager(
            config = params.pagingConfig,
            pagingSourceFactory = { ContentPagingSource(repository, params.options) }
        ).flow
    }
}