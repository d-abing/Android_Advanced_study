package com.example.fastcampusadvanced.chapter4.mvvm.model.repository

import com.example.fastcampusadvanced.chapter4.RetrofitManager
import com.example.fastcampusadvanced.chapter4.mvvm.model.Image
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ImageRepositoryImpl : ImageRepository {
    // Model
    // Retrofit을 사용하여 네트워크 요청을 수행하고, RxJava를 사용하여 비동기 처리합니다.

    override fun getRandomImage() = RetrofitManager.imageService.getRandomImageRx()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .flatMap { item ->
            Single.just(Image(item.urls.regular, item.color))
        }
}