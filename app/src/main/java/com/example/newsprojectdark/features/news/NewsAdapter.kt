package com.example.newsprojectdark.features.news

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.framework.core.base.binding.BindingViewHolder
import com.example.framework.core.base.binding.toBinding
import com.example.framework.extensions.cast
import com.example.model.dto.NewContentDto
import com.example.newsprojectdark.R
import com.example.newsprojectdark.databinding.RowNewBinding

class NewsAdapter : PagingDataAdapter<NewContentDto, RecyclerView.ViewHolder>(
    NewsComparator
) {
    companion object NewsComparator : DiffUtil.ItemCallback<NewContentDto>() {
        override fun areItemsTheSame(oldItem: NewContentDto, newItem: NewContentDto) =
            oldItem.id == newItem.id && oldItem.isReadLaterElement == newItem.isReadLaterElement

        override fun areContentsTheSame(oldItem: NewContentDto, newItem: NewContentDto) =
            oldItem == newItem
    }

    var onClickItem: ((NewContentDto) -> Unit)? = null
    var onClickReadLater: ((NewContentDto) -> Unit)? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val news = getItem(position)
        news?.let { holder.cast<NewsViewHolder>().bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NewsViewHolder(parent.toBinding())
    }

    inner class NewsViewHolder(binding: RowNewBinding) :
        BindingViewHolder<RowNewBinding>(binding) {

        fun bind(item: NewContentDto) {
            binding.tvTitle.text = item.webTitle
            binding.tvPublicationDate.text = item.webPublicationDate

            if (item.isReadLaterElement) {
                binding.btnReadLater.setImageResource(R.drawable.ic_full_bookmark)
            } else {
                binding.btnReadLater.setImageResource(R.drawable.ic_bookmark)
            }

            binding.btnReadLater.setOnClickListener {
                item.isReadLaterElement = !item.isReadLaterElement
                binding.btnReadLater.startAnimation(item.isReadLaterElement) {
                    onClickReadLater?.invoke(item)
                }
            }

            binding.root.setOnClickListener {
                onClickItem?.invoke(item)
            }
        }
    }
}