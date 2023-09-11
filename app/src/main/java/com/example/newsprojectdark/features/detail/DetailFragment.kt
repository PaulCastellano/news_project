package com.example.newsprojectdark.features.detail

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.framework.core.base.mvi.MviFragment
import com.example.framework.extensions.argument
import com.example.newsprojectdark.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
MviFragment<FragmentDetailBinding, DetailContract.State, DetailViewModel>() {
    companion object {
        private const val ARG_DETAIL_ID = "arg_detail_id"

        @JvmStatic
        fun newInstance(detailId: String) = DetailFragment().apply {
            arguments = bundleOf(ARG_DETAIL_ID to detailId)
        }
    }

    private val detailId: String by argument(ARG_DETAIL_ID)

    override val viewModel: DetailViewModel by viewModels()

    override fun onViewReady(bundle: Bundle?) {
        val detailId = detailId
        viewModel.onTriggerEvent(DetailContract.Event.LoadDetail(detailId))
    }

    override fun onViewListener() {
        super.onViewListener()
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun renderViewState(viewState: DetailContract.State) {

        when (viewState) {
            is DetailContract.State.ContentDetail -> {
                val detail = viewState.detail
                binding.webView.loadUrl(detail.webUrl)
                binding.webView.webViewClient = WebViewClient()
            }
        }
    }
}