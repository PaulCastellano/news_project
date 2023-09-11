package com.example.framework.core.base.mvvm
import androidx.viewbinding.ViewBinding
import com.example.framework.core.base.core.CoreActivity
import com.example.framework.extensions.observeFlowStart

abstract class MvvmActivity<VB : ViewBinding, VM : MvvmViewModel> : CoreActivity<VB>() {

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
