package com.example.newsprojectdark.features.readlater

import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.framework.extensions.argument
import com.example.framework.extensions.setSafeOnClickListener
import com.example.model.dto.NewContentDto
import com.example.newsprojectdark.R
import com.example.newsprojectdark.base.BaseSheetDialog
import com.example.newsprojectdark.databinding.DialogRemoveReadLaterBinding

class RemoveReadLaterDialog : BaseSheetDialog<DialogRemoveReadLaterBinding>() {

    companion object {
        private const val ARG_FAVOR = "arg_favor"

        @JvmStatic
        fun newInstance(dto: NewContentDto) = RemoveReadLaterDialog().apply {
            arguments = bundleOf(ARG_FAVOR to dto)
        }
    }

    private val News: NewContentDto? by argument(ARG_FAVOR)

    var result: (NewsId: String) -> Unit = {}

    override fun onViewReady(bundle: Bundle?) {
        News?.let {
            binding.tvName.text = it.webTitle
            binding.tvDesc.text = getString(R.string.text_delete_read_later_description, it.webTitle)
        }
    }

    override fun onViewListener() {
        super.onViewListener()
        binding.tvCancel.setSafeOnClickListener {
            dismissAllowingStateLoss()
        }

        binding.btnApproveDelete.setSafeOnClickListener {
            dismissAllowingStateLoss()
            News?.let { result.invoke(it.id) }
        }
    }
}