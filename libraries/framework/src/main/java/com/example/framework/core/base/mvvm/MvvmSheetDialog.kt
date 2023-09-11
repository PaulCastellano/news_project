package com.example.framework.core.base.mvvm

import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.core.CoreSheetDialog
import com.example.framework.extensions.observeFlowStart

abstract class MvvmSheetDialog<VB : ViewBinding, VM : MvvmViewModel> : CoreSheetDialog<VB>() {

    abstract val viewModel: VM

    open fun showProgress() {}

    open fun hideProgress() {}

    open fun showError(throwable: Throwable) {}

    override fun observeUi() {
        super.observeUi()
        observeProgress()
    }

    private fun observeProgress() {
        observeFlowStart(viewModel.progress) { state ->
            state?.let {
                if (it) {
                    showProgress()
                } else {
                    hideProgress()
                }
            }
        }
    }
}
