package com.example.newsprojectdark.features.news

import androidx.paging.PagingData
import com.example.model.dto.NewContentDto

class NewsContract {
    sealed class Event {
        object Loadnews : Event()
        data class UpdateReadLater(val NewsDto: NewContentDto) : Event()
    }

    sealed class State {
        data class News(val pagedData: PagingData<NewContentDto> = PagingData.empty()) : State()
    }
}