package com.example.fastcampusadvanced.chapter4.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastcampusadvanced.chapter4.mvvm.model.Image
import com.example.fastcampusadvanced.chapter4.mvvm.model.repository.ImageRepository
import io.reactivex.disposables.CompositeDisposable

class MvvmViewModel(private val imageRepository: ImageRepository) : ViewModel() {
    // ViewModel
    // UI 관련 데이터를 보유하고, LiveData를 사용하여 데이터를 관찰 가능하게 만듭니다.

    private val _countLiveData = MutableLiveData<String>()
    val countLiveData: LiveData<String> by lazy { _countLiveData }

    private val _imageLiveData = MutableLiveData<Image>()
    val imageLiveData: LiveData<Image> by lazy { _imageLiveData }

    private var disposable: CompositeDisposable? = CompositeDisposable()
    private var count = 0

    fun loadRandomImage() {
        disposable?.add(imageRepository.getRandomImage()
            .doOnSuccess {
                count++
            }
            .subscribe { item ->
                _imageLiveData.value = item
                _countLiveData.value = "불러온 이미지 수 : $count"
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable = null
    }

    class MvvmViewModelFactory(private val imageRepository: ImageRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MvvmViewModel(imageRepository) as T
        }
    }
}