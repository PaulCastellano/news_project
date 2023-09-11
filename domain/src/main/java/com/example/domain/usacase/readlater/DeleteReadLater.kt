package com.example.domain.usacase.readlater

import com.example.framework.core.usecase.LocalUseCase
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteReadLater @Inject constructor(
    private val repository: ContentRepository
) : LocalUseCase<DeleteReadLater.Params, Unit>(){
    data class Params(
        val NewsId: String
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        repository.deleteReadLaterById(params.NewsId)
        emit(Unit)
    }
}