package com.example.fastcampusadvanced.chapter5.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.fastcampusadvanced.chapter5.list.ItemHandler
import com.example.fastcampusadvanced.chapter5.model.ImageItem
import com.example.fastcampusadvanced.chapter5.model.ListItem
import com.example.fastcampusadvanced.databinding.ItemImageBinding

class ImageViewHolder(
    private val binding: ItemImageBinding,
    private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ListItem) {
        item as ImageItem
        binding.item = item
        binding.handler = itemHandler
    }
}