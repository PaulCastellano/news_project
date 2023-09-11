package com.example.framework.core.base.mvi

import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.mvvm.MvvmFragment
import com.example.framework.extensions.observeFlowStart

abstract class MviFragment<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MvvmFragment<VB, VM>() {

    abstract fun renderViewState(viewState: STATE)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.stateFlow, ::renderViewState)
    }
}