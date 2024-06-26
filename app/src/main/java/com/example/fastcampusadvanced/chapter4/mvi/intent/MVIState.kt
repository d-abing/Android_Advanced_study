package com.example.fastcampusadvanced.chapter4.mvi.intent

import com.example.fastcampusadvanced.chapter4.mvi.model.Image

sealed class MVIState {
    object Idle : MVIState()
    object Loading : MVIState()
    data class LoadedImage(val image: Image, val count: Int) : MVIState()
}