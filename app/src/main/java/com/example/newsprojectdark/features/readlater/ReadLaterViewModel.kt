package com.example.newsprojectdark.features.readlater

import com.example.domain.usacase.readlater.DeleteReadLater
import com.example.domain.usacase.readlater.GetReadLater
import com.example.framework.core.base.mvi.MviViewModel
import com.example.newsprojectdark.features.readlater.ReadLaterContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ReadLaterViewModel @Inject constructor(
    private val getReadLater: GetReadLater,
    private val deleteReadLater: DeleteReadLater
) : MviViewModel<ReadLaterContract.State, ReadLaterContract.Event>() {

    override fun onTriggerEvent(eventType: ReadLaterContract.Event) {
        when (eventType) {
            is ReadLaterContract.Event.LoadReadLAter -> handleLoadReadLater()
            is ReadLaterContract.Event.DeleteItem -> handleDeleteItem(eventType.id)
        }
    }

    private fun handleDeleteItem(id: String) = safeLaunch {
        call(deleteReadLater(DeleteReadLater.Params(id))) {
            onTriggerEvent(ReadLaterContract.Event.LoadReadLAter)
        }
    }

    private fun handleLoadReadLater() = safeLaunch {
        callWithProgress(getReadLater.invoke(Unit)) {
            setState(ReadLaterContract.State.ReadLater(it))
        }
    }
}