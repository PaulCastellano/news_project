package com.example.domain.usacase.readlater

import com.example.framework.core.usecase.LocalUseCase
import com.example.model.dto.NewContentDto
import com.example.model.dto.toReadLaterDtoList
import com.example.repository.news.ContentRepository
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetReadLater @Inject constructor(
    private val repository: ContentRepository
) : LocalUseCase<Unit, List<NewContentDto>>() {

    override suspend fun FlowCollector<List<NewContentDto>>.execute(params: Unit) {
        val favors = repository.getReadLaterList()
        emit(favors.toReadLaterDtoList())
    }
}