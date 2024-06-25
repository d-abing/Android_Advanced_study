package com.example.fastcampusadvanced.chapter3

import androidx.recyclerview.widget.RecyclerView
import com.example.fastcampusadvanced.chapter3.model.DetailItem
import com.example.fastcampusadvanced.databinding.ItemDetailBinding

class DetailViewHolder(private val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DetailItem) {
        binding.item = item
    }
}