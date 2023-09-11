package com.example.newsprojectdark.features.detail

import com.example.domain.usacase.content.GetContentDetail
import com.example.framework.core.base.mvi.MviViewModel
import com.example.model.dto.NewContentDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getContentDetail: GetContentDetail
) : MviViewModel<DetailContract.State, DetailContract.Event>() {

    private var newContentDto: NewContentDto? = null


    override fun onTriggerEvent(eventType: DetailContract.Event) {
        when (eventType) {
            is DetailContract.Event.LoadDetail -> loadDetail(eventType.id)
            else -> {}
        }
    }

    private fun loadDetail(id: String) = safeLaunch {
        val params = GetContentDetail.Params(id)
        executeWithProgress(getContentDetail(params)) { dto ->
            this@DetailViewModel.newContentDto = dto
            setState(DetailContract.State.ContentDetail(dto))
        }
    }
}