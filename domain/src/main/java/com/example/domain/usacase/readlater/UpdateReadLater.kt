package com.example.domain.usacase.readlater

import com.example.framework.core.usecase.LocalUseCase
import com.example.model.dto.NewContentDto
import com.example.model.dto.toReadLaterEntity
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class UpdateReadLater @Inject constructor(
    private val repository: ContentRepository
) : LocalUseCase<UpdateReadLater.Params, Unit>() {

    data class Params(
        val News: NewContentDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.News
        val News = repository.getReadLater(dto.id)
        if (News == null) {
            repository.saveReadLater(dto.toReadLaterEntity())
        } else {
            repository.deleteReadLaterById(dto.id)
        }
        emit(Unit)
    }
}