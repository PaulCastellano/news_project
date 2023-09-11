package com.example.newsprojectdark.features.news

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.usacase.content.GetAllContent
import com.example.domain.usacase.readlater.UpdateReadLater
import com.example.framework.core.base.mvi.MviViewModel
import com.example.model.dto.NewContentDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getnews: GetAllContent,
    private val updateReadLater: UpdateReadLater
) : MviViewModel<NewsContract.State, NewsContract.Event>() {
    private val config = PagingConfig(pageSize = 20)

    override fun onTriggerEvent(eventType: NewsContract.Event) {
        when (eventType) {
            is NewsContract.Event.Loadnews -> loadNews()
            is NewsContract.Event.UpdateReadLater -> updateReadLater(eventType.NewsDto)
        }
    }

    private fun loadNews() = safeLaunch {
        val params = GetAllContent.Params(config, hashMapOf())
        getnews(params).cachedIn(scope = viewModelScope)
            .collect {
                setState(NewsContract.State.News(it))
            }
    }

    private fun updateReadLater(dto: NewContentDto) = safeLaunch {
        val params = UpdateReadLater.Params(dto)
        call(updateReadLater(params))
    }
}