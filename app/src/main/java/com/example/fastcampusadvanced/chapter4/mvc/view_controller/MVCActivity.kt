package com.example.fastcampusadvanced.chapter4.mvc.view_controller

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.fastcampusadvanced.chapter4.mvc.model.ImageCountModel
import com.example.fastcampusadvanced.chapter4.mvc.model.ImageProvider
import com.example.fastcampusadvanced.databinding.ActivityMvcBinding

class MVCActivity : AppCompatActivity(), ImageProvider.Callback {
    // View, Controller
    // 사용자 인터페이스(UI)를 렌더링하고 사용자 입력을 받음 (View 역할)
    // 사용자 입력을 처리하고, 모델을 업데이트하며, 모델 데이터를 뷰에 반영 (Controller 역할)

    private lateinit var binding: ActivityMvcBinding
    private val model = ImageCountModel()
    private val imageProvider = ImageProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }
    }

    fun loadImage() {
        imageProvider.getRandomImage()
    }

    override fun loadImage(url: String, color: String) {
        model.increase()
        with(binding) {
            randomIv.run {
                setBackgroundColor(Color.parseColor(color))
                load(url)
            }
            imageCountTv.text = "불러온 이미지 수 : ${model.count}"
        }
    }
}