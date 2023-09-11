package com.example.newsprojectdark.features.detail

import com.example.model.dto.NewContentDto

class DetailContract {
    sealed class Event {
        data class LoadDetail(val id: String = "") : Event()
    }

    sealed class State {
        data class ContentDetail(val detail: NewContentDto) : State()
    }
}