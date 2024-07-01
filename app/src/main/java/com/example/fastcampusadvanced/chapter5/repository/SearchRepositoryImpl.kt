package com.example.fastcampusadvanced.chapter5.repository

import com.example.fastcampusadvanced.chapter5.SearchService
import com.example.fastcampusadvanced.chapter5.model.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {

    override fun search(query: String): Observable<List<ListItem>> {
        return searchService.searchImage(query)
            .zipWith(searchService.searchVideo(query)) { images, videos ->
                mutableListOf<ListItem>().apply {
                    addAll(images.documents)
                    addAll(videos.documents)
                }.sortedBy { it.dateTime }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}