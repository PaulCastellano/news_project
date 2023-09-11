package com.example.newsprojectdark.features.news

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.components.adapter.PagingLoadStateAdapter
import com.example.framework.extensions.observeFlow
import com.example.newsprojectdark.base.mvi.BaseMviFragment
import com.example.newsprojectdark.databinding.FragmentNewsBinding
import com.example.newsprojectdark.provider.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import setItemDecoration
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment :
    BaseMviFragment<FragmentNewsBinding, NewsContract.State, NewsViewModel>() {
    companion object {
        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    @Inject
    lateinit var navigationProvider: NavigationProvider

    private val adapter = NewsAdapter()

    override val viewModel by viewModels<NewsViewModel>()

    override fun onViewReady(bundle: Bundle?) {
        initAdapter()
    }

    private fun initAdapter() {
        binding.rvnews.setHasFixedSize(true)
        binding.rvnews.layoutManager = LinearLayoutManager(requireContext())
        binding.rvnews.setItemDecoration(left = 8, top = 12, right = 8, bottom = 0)
        binding.rvnews.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PagingLoadStateAdapter(adapter),
            footer = PagingLoadStateAdapter(adapter)
        )
        binding.swrnews.setOnRefreshListener { adapter.refresh() }
        adapter.onClickItem = {
            navigationProvider.launchDetailFragment(this, it.id)
        }
        adapter.onClickReadLater = {
            viewModel.onTriggerEvent(NewsContract.Event.UpdateReadLater(it))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onTriggerEvent(NewsContract.Event.Loadnews)
    }

    override fun renderViewState(viewState: NewsContract.State) {
        when (viewState) {
            is NewsContract.State.News -> {
                adapter.submitData(lifecycle, viewState.pagedData)
            }
        }
    }

    override fun observeUi() {
        super.observeUi()
        observeFlow(adapter.loadStateFlow) {
            binding.swrnews.isRefreshing = it.refresh is LoadState.Loading
        }
    }
}