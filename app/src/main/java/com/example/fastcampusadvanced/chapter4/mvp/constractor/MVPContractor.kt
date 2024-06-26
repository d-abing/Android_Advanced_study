package com.example.fastcampusadvanced.chapter4.mvp.constractor

interface MVPContractor {
    // 계약(Contract) 역할을 하며, View와 Presenter의 인터페이스를 정의합니다.
    // View와 Presenter 간의 상호작용을 정의합니다.

    interface View {
        fun showImage(url: String, color: String)
        fun showImageCountText(count: Int)

    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun loadRandomImage()

    }
}