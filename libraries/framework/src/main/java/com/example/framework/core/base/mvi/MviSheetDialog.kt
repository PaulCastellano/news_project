package com.example.framework.core.base.mvi

import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.mvvm.MvvmSheetDialog
import com.example.framework.extensions.observeFlowStart

abstract class MviSheetDialog<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MvvmSheetDialog<VB, VM>() {

    abstract fun renderViewState(viewState: STATE)

    override fun observeUi() {
        super.observeUi()
        observeFlowStart(viewModel.stateFlow, ::renderViewState)
    }
}