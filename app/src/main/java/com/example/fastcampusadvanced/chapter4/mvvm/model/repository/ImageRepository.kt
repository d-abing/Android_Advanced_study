package com.example.fastcampusadvanced.chapter4.mvvm.model.repository

import com.example.fastcampusadvanced.chapter4.mvvm.model.Image
import io.reactivex.Single

interface ImageRepository {
    // Model
    // Model의 인터페이스 역할을 하며, 데이터를 가져오는 방법을 정의합니다.
    fun getRandomImage(): Single<Image>
}