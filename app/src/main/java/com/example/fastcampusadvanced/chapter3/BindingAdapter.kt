package com.example.fastcampusadvanced.chapter3

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.fastcampusadvanced.R
import com.example.fastcampusadvanced.chapter3.model.Type
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("date")
fun TextView.setDate(date: Date?) {
    if (date == null) {
        return
    }

    text = SimpleDateFormat("yyyy.MM.dd").run {
        format(date) ?: format(Date())
    }
}

@BindingAdapter("type")
fun TextView.setType(type: Type) {
    when (type) {
        Type.POINT -> {
            text = "포인트"
            (background as GradientDrawable).setColor(
                ContextCompat.getColor(
                    context,
                    R.color.point
                )
            )
        }
        Type.CANCEL -> {
            text = "취소"
            (background as GradientDrawable).setColor(
                ContextCompat.getColor(
                    context,
                    R.color.cancel
                )
            )
        }
        else -> {
            text = "결제"
            (background as GradientDrawable).setColor(
                ContextCompat.getColor(
                    context,
                    R.color.pay
                )
            )
        }
    }
}

@BindingAdapter("amount")
fun TextView.setAmount(amount: Long) {
    text = NumberFormat.getInstance(Locale.KOREA).format(amount) + "원"
}