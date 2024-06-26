package com.example.fastcampusadvanced.chapter4.mvc.model

import com.example.fastcampusadvanced.chapter4.ImageResponse
import com.example.fastcampusadvanced.chapter4.RetrofitManager
import retrofit2.Call
import retrofit2.Response

class ImageProvider(private val callback: Callback) {
    // Model
    // 랜덤 이미지를 가져오는 비즈니스 로직을 수행
    // Retrofit을 통해 네트워크 요청을 수행하고, 콜백을 통해 결과를 MVCActivity에 전달

    fun getRandomImage() {
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

    interface Callback {
        fun loadImage(url: String, color: String)
    }
}