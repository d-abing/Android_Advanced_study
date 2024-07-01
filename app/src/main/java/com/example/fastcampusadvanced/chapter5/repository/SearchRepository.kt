package com.example.fastcampusadvanced.chapter5.repository

import com.example.fastcampusadvanced.chapter5.model.ListItem

interface SearchRepository {

    fun search(query: String): io.reactivex.rxjava3.core.Observable<List<ListItem>>
}