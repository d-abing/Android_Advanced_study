package com.example.fastcampusadvanced.chapter4.mvi.model.repository

import com.example.fastcampusadvanced.chapter4.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage(): Image
}