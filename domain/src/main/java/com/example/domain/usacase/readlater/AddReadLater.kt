package com.example.domain.usacase.readlater

import com.example.framework.core.usecase.LocalUseCase
import com.example.model.dto.NewContentDto
import com.example.model.dto.toReadLaterEntity
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class AddReadLater
@Inject constructor(
    private val repository: ContentRepository
): LocalUseCase<AddReadLater.Params, Unit>() {

    data class Params(
        val News: NewContentDto
    )

    override suspend fun FlowCollector<Unit>.execute(params: Params) {
        val dto = params.News
        repository.saveReadLater(dto.toReadLaterEntity())
        emit(Unit)
    }
}