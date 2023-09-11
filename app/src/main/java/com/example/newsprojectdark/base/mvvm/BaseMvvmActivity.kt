package com.example.newsprojectdark.base.mvvm

import androidx.viewbinding.ViewBinding
import com.example.components.ProgressDialog
import com.example.framework.core.base.mvvm.MvvmActivity
import com.example.framework.core.base.mvvm.MvvmViewModel
import com.example.framework.extensions.showSnackBar

abstract class BaseMvvmActivity<VB : ViewBinding, VM : MvvmViewModel> :
    MvvmActivity<VB, VM>() {

    private var progressDialog: ProgressDialog? = null

    override fun showProgress() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this)
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