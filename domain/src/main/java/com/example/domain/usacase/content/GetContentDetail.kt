package com.example.domain.usacase.content

import com.example.framework.core.network.DataState
import com.example.framework.core.network.apiCall
import com.example.framework.core.usecase.DataStateUseCase
import com.example.model.dto.NewContentDto
import com.example.model.dto.toNewContentDto
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetContentDetail
@Inject constructor(
    private val repository: ContentRepository
): DataStateUseCase<GetContentDetail.Params, NewContentDto>() {

    data class Params(
        val detailId: String
    )

    override suspend fun FlowCollector<DataState<NewContentDto>>.execute(params: Params) {
        val service = apiCall {
            repository.getContent(params.detailId).itemInfo.content.toNewContentDto()
        }
        service.map {
            val NewsFav = repository.getReadLater(it.id)
            it.isReadLaterElement = NewsFav != null
        }
        emit(service)
    }
}