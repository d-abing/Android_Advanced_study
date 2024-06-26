package com.example.fastcampusadvanced.chapter4.mvi.model.repository

import com.example.fastcampusadvanced.chapter4.RetrofitManager
import com.example.fastcampusadvanced.chapter4.mvi.model.Image
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ImageRepository {

    override suspend fun getRandomImage() = withContext(dispatcher) {

        RetrofitManager.imageService.getRandomImageSuspend().let {
            Image(it.urls.regular, it.color)
        }
    }
}