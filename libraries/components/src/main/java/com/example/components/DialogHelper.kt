package com.example.components

import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.example.components.databinding.DialogInternetCheckBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

object DialogHelper {
    fun showInternetCheckDialog(context: Context, handler: () -> Unit) {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
        val layoutInflater = LayoutInflater.from(context)
        val binding = DialogInternetCheckBinding.inflate(layoutInflater)
        dialogBuilder.setView(binding.root)

        val alertDialog = dialogBuilder.create().apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCanceledOnTouchOutside(false)
            val decorView = window?.decorView
            decorView?.setBackgroundResource(android.R.color.transparent)
        }

        alertDialog.show()

        binding.btnOk.setOnClickListener {
            alertDialog.dismiss()
            handler()
        }
    }
}