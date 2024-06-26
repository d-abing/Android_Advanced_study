package com.example.fastcampusadvanced.chapter4.mvp.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.fastcampusadvanced.chapter4.mvc.model.ImageCountModel
import com.example.fastcampusadvanced.chapter4.mvp.constractor.MVPContractor
import com.example.fastcampusadvanced.chapter4.mvp.model.repository.ImageRepositoryImpl
import com.example.fastcampusadvanced.chapter4.mvp.presenter.MVPPresenter
import com.example.fastcampusadvanced.databinding.ActivityMvpBinding

class MVPActivity : AppCompatActivity(), MVPContractor.View {
    // View
    // Presenter와 상호작용하여 데이터를 표시하고, 사용자 입력을 전달합니다.

    private lateinit var binding: ActivityMvpBinding
    private lateinit var presenter: MVPContractor.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

        presenter = MVPPresenter(ImageCountModel(), ImageRepositoryImpl())
        presenter.attachView(this)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    fun loadImage() {
        presenter.loadRandomImage()
    }

    override fun showImage(url: String, color: String) {
        binding.randomIv.run {
            setBackgroundColor(Color.parseColor(color))
            load(url) {
                crossfade(300)
            }
        }
    }

    override fun showImageCountText(count: Int) {
        binding.imageCountTv.text = "불러온 이미지 수 : $count"
    }


}