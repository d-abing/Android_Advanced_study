package com.example.fastcampusadvanced.chapter4.mvp.presenter

import com.example.fastcampusadvanced.chapter4.mvc.model.ImageCountModel
import com.example.fastcampusadvanced.chapter4.mvp.constractor.MVPContractor
import com.example.fastcampusadvanced.chapter4.mvp.model.repository.ImageRepository

class MVPPresenter(
    // Presenter
    // View와 상호작용하여 사용자 입력을 처리하고, Model을 업데이트합니다.
    // Model로부터 데이터를 가져와 View에 전달합니다.

    private val model: ImageCountModel,
    private val imageRepository: ImageRepository
) : MVPContractor.Presenter, ImageRepository.Callback {

    private var view: MVPContractor.View? = null

    override fun attachView(view: MVPContractor.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadRandomImage() {
        imageRepository.getRandomImage(this)
    }

    override fun loadImage(url: String, color: String) {
        model.increase()
        view?.showImage(url, color)
        view?.showImageCountText(model.count)
    }
}