package com.example.fastcampusadvanced.chapter5

import com.example.fastcampusadvanced.chapter5.model.ImageListResponse
import com.example.fastcampusadvanced.chapter5.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @Headers("Authorization: KakaoAK $KAKAO_ACCESS_KEY")
    @GET("image")
    fun searchImage(@Query("query") query: String): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK $KAKAO_ACCESS_KEY")
    @GET("vclip")
    fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
}