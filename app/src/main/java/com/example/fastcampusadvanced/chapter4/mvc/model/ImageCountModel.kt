package com.example.fastcampusadvanced.chapter4.mvc.model

class ImageCountModel {
    // Model
    // 이미지 로드 횟수를 관리하고, 증가시키는 로직을 포함

    var count = 0

    fun increase() {
        count += 1
    }
}