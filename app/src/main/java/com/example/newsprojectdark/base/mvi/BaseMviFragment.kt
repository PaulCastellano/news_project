package com.example.newsprojectdark.base.mvi

import androidx.viewbinding.ViewBinding
import com.example.components.ProgressDialog
import com.example.framework.core.base.mvi.MviFragment
import com.example.framework.core.base.mvi.MviViewModel
import com.example.framework.extensions.showSnackBar

abstract class BaseMviFragment<VB : ViewBinding, STATE, VM : MviViewModel<STATE, *>> :
    MviFragment<VB, STATE, VM>() {

    private var progressDialog: ProgressDialog? = null

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(requireContext())
        }
        progressDialog?.show()
    }

    override fun hideProgress() {
        progressDialog?.dismiss()
    }

    override fun showError(throwable: Throwable) {
        handleErrorMessage(throwable.message.toString())
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        hideProgress()
        showSnackBar(binding.root, message)
    }
}