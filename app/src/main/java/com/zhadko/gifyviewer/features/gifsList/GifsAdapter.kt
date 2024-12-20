package com.zhadko.gifyviewer.features.gifsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhadko.gifyviewer.R
import com.zhadko.gifyviewer.databinding.ItemGifBinding
import com.zhadko.gifyviewer.domain.models.Gif
import com.zhadko.gifyviewer.extensions.loadAsGif

class GifsAdapter(
    private val onClick: (Gif) -> Unit,
) : PagingDataAdapter<Gif,
        GifsAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val gif = getItem(position)
        if (gif != null) {
            holder.bind(gif, onClick)
        }
    }

    class ItemViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Gif, click: (Gif) -> Unit) {
            with(binding) {
                gifTitleView.text = data.title
                Glide.with(binding.root)
                    .loadAsGif(data.path, gifsView, R.drawable.ic_launcher_background)
                root.setOnClickListener {
                    click(data)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): ItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGifBinding
                    .inflate(layoutInflater, parent, false)
                return ItemViewHolder(binding)
            }
        }
    }

    class ItemDiffUtilCallback : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.path == newItem.path
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem == newItem
        }
    }

}