package com.example.newsprojectdark.features.readlater

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.components.adapter.BasicSwipeAdapter
import com.example.components.swipemenulayout.MenuState
import com.example.framework.core.base.binding.toBinding
import com.example.model.dto.NewContentDto
import com.example.newsprojectdark.databinding.RowReadLaterBinding

class ReadLaterAdapter : BasicSwipeAdapter<NewContentDto, RowReadLaterBinding>() {
    var onClickItem: ((NewContentDto) -> Unit)? = null

    fun openPreview() {
        binderHelper.showPreviewSwipe(MenuState.RIGHT_OPEN)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup): RowReadLaterBinding {
        return parent.toBinding()
    }

    override fun bindView(binding: RowReadLaterBinding, position: Int, item: NewContentDto) {
        binding.tvTitle.text = item.webTitle
        binding.tvPublicationDate.text = item.webPublicationDate
        binding.root.setOnClickListener {
            onClickItem?.invoke(item)
        }
    }
}