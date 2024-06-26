package com.example.fastcampusadvanced.chapter4.mvp.model.repository

interface ImageRepository {
    // Model
    // Model의 인터페이스 역할을 하며, 데이터를 가져오는 방법을 정의합니다.
    fun getRandomImage(callback: Callback)

    interface Callback {
        fun loadImage(url: String, color: String)
    }
}