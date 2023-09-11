package com.example.newsprojectdark.features.readlater

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.components.swipemenulayout.SwipeMenuItem
import com.example.components.swipemenulayout.menuItems
import com.example.framework.core.font.FontCache
import com.example.model.dto.NewContentDto
import com.example.newsprojectdark.R
import com.example.newsprojectdark.base.mvi.BaseMviFragment
import com.example.newsprojectdark.databinding.FragmentReadLaterBinding
import com.example.newsprojectdark.provider.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint
import setItemDecoration
import javax.inject.Inject

@AndroidEntryPoint
class ReadLaterFragment :
    BaseMviFragment<FragmentReadLaterBinding, ReadLaterContract.State, ReadLaterViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = ReadLaterFragment()
    }

    @Inject
    lateinit var navigationProvider: NavigationProvider

    private val adapter = ReadLaterAdapter()

    override val viewModel by viewModels<ReadLaterViewModel>()

    override fun onViewReady(bundle: Bundle?) {
        initAdapter()
    }

    override fun renderViewState(viewState: ReadLaterContract.State) {
        when (viewState) {
            is ReadLaterContract.State.ReadLater -> {
                adapter.setItems(viewState.list)
                adapter.openPreview()
            }
        }
    }

    private fun initAdapter() {
        binding.rvReadLater.setHasFixedSize(true)
        binding.rvReadLater.layoutManager = LinearLayoutManager(requireContext())
        binding.rvReadLater.setItemDecoration(left = 8, top = 12, right = 8, bottom = 0)
        binding.rvReadLater.adapter = adapter
        adapter.rightSwipeMenus = getRightMenus()
        adapter.onClickItem = {
            navigationProvider.launchDetailFragment(this, it.id)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onTriggerEvent(ReadLaterContract.Event.LoadReadLAter)
    }

    private fun getRightMenus(): List<SwipeMenuItem> {
        return menuItems {
            menuItem {
                width = 80
                height = ViewGroup.LayoutParams.MATCH_PARENT
                orientation = LinearLayoutCompat.VERTICAL
                title = getString(R.string.text_remove)
                titleSize = 14
                titleColor = R.color.white
                iconRes = R.drawable.ic_trash
                backgroundColor = R.color.red_primary_dark
                textTypeface = FontCache[R.font.raleway_semi_bold, requireContext()]
                menuItemClick = { position ->
                    showDeleteDialog(adapter.getItem(position))
                }
            }
        }
    }

    private fun showDeleteDialog(dto: NewContentDto) {
        val dialog = RemoveReadLaterDialog.newInstance(dto)
        dialog.show(childFragmentManager, dialog.tag)
        dialog.result = { NewsId: String ->
            viewModel.onTriggerEvent(
                ReadLaterContract.Event.DeleteItem(NewsId)
            )
        }
    }
}