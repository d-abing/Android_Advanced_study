package com.example.fastcampusadvanced.chapter4

data class ImageResponse(
    val id: String,
    val urls: UrlResponse,
    val color: String,
)

data class UrlResponse(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)
