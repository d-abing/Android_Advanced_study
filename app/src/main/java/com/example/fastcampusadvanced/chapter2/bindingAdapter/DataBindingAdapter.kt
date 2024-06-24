package com.example.fastcampusadvanced.chapter2.bindingAdapter

import android.os.Build.VERSION_CODES.P
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.fastcampusadvanced.R

@BindingAdapter(value = ["code_text", "code_index"])
fun ImageView.setPin(codeText: CharSequence?, index: Int) {
    if (codeText != null) {
        if (codeText.length > index) {
            setImageResource(R.drawable.baseline_circle_black_24)
        } else {
            setImageResource(R.drawable.baseline_circle_gray_24)
        }
    }
}