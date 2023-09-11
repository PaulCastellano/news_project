package com.example.framework.core.base.mvi

import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.mvvm.MvvmActivity
import com.example.framework.extensions.observeFlowStart

abstract class MviActivity<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MvvmActivity<VB, VM>() {

    abstract fun renderViewState(viewState: STATE)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.stateFlow, ::renderViewState)
    }
}