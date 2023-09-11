package com.example.newsprojectdark.features.readlater

import com.example.model.dto.NewContentDto

class ReadLaterContract {
    sealed class Event {
        object LoadReadLAter : Event()
        data class DeleteItem(val id: String) : Event()
    }

    sealed class State {
        data class ReadLater(val list: List<NewContentDto>) : State()
    }
}