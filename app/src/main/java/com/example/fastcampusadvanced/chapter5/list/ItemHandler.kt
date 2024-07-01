package com.example.fastcampusadvanced.chapter5.list

import com.example.fastcampusadvanced.chapter5.model.ListItem

interface ItemHandler {
    fun onClickFavorite(item: ListItem)
}