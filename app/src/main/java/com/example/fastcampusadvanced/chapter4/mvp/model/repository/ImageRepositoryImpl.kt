package com.example.fastcampusadvanced.chapter4.mvp.model.repository

import com.example.fastcampusadvanced.chapter4.ImageResponse
import com.example.fastcampusadvanced.chapter4.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class ImageRepositoryImpl : ImageRepository {
    // Model
    // Model의 구현체로, 실제 데이터를 가져오는 로직을 포함합니다.
    // Retrofit을 통해 네트워크 요청을 수행하고, 콜백을 통해 결과를 MVPActivity로 전달합니다.

    override fun getRandomImage(callback: ImageRepository.Callback) {
        RetrofitManager.imageService.getRandomImage()
            .enqueue(object : retrofit2.Callback<ImageResponse> {
                override fun onResponse(
                    call: Call<ImageResponse>,
                    response: Response<ImageResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.loadImage(it.urls.regular, it.color)
                        }
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
    }
}