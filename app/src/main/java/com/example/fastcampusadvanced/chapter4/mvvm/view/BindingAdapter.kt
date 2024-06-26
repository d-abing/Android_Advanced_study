package com.example.fastcampusadvanced.chapter4.mvvm.view

import android.graphics.Color
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.fastcampusadvanced.chapter4.mvvm.model.Image

// View
// Data Binding 라이브러리를 사용하여 ViewModel의 데이터를 View에 바인딩합니다.

@BindingAdapter("image")
fun ImageView.setImage(image: Image?) {
    if (image == null) {
        return
    }

    setBackgroundColor(Color.parseColor(image.color))

    load(image.url) {
        crossfade(300)
    }

}