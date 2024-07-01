package com.example.fastcampusadvanced.chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.fastcampusadvanced.chapter5.list.ItemHandler
import com.example.fastcampusadvanced.chapter5.model.ListItem
import com.example.fastcampusadvanced.chapter5.model.VideoItem
import com.example.fastcampusadvanced.databinding.ItemVideoBinding

class VideoViewHolder(
    private val binding: ItemVideoBinding,
    private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as VideoItem
        binding.item = item
        binding.handler = itemHandler
    }
}