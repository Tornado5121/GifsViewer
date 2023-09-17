package com.zhadko.gifyviewer.features.gifsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zhadko.gifyviewer.R
import com.zhadko.gifyviewer.common.ImageLoader
import com.zhadko.gifyviewer.databinding.ItemGifBinding
import com.zhadko.gifyviewer.domain.models.Gif

class GifsAdapter(
    private val imageLoader: ImageLoader,
    private val onClick: (Gif) -> Unit,
) : ListAdapter<Gif,
        GifsAdapter.ItemViewHolder>(ItemDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position], imageLoader, onClick)
    }

    class ItemViewHolder(private val binding: ItemGifBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Gif, imageLoader: ImageLoader, click: (Gif) -> Unit) {
            with(binding) {
                gifTitleView.text = data.title
                imageLoader.loadAsGif(data.path, gifsView, R.drawable.ic_launcher_background)
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