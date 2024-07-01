package com.example.fastcampusadvanced.chapter5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fastcampusadvanced.chapter5.model.ImageItem
import com.example.fastcampusadvanced.chapter5.model.ListItem
import com.example.fastcampusadvanced.chapter5.model.VideoItem
import com.example.fastcampusadvanced.chapter5.repository.SearchRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(private val searchRepository: SearchRepository) : ViewModel() {

    private val _listLiveData = MutableLiveData<List<ListItem>>()
    val listLiveData: MutableLiveData<List<ListItem>> get() = _listLiveData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: MutableLiveData<Boolean> get() = _isLoading

    private var disposable: CompositeDisposable? = CompositeDisposable()


    fun search(query: String) {
        disposable?.add(
            searchRepository.search(query)
                .doOnSubscribe { _isLoading.postValue(true) }
                .doOnTerminate { _isLoading.postValue(false) }
                .subscribe({ list ->
                    _listLiveData.value = list
                }, {
                    _listLiveData.value = emptyList()
                })
        )
    }

    fun toggleFavorite(item: ListItem) {
        _listLiveData.value = _listLiveData.value?.map {
            if (it == item) {
                when (it) {
                    is ImageItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }

                    is VideoItem -> {
                        it.copy(isFavorite = !item.isFavorite)
                    }

                    else -> {
                        it
                    }
                }.also { changedItem ->
                    if (Common.favoriteList.contains(item)) {
                        Common.favoriteList.remove(item)
                    } else {
                        Common.favoriteList.add(changedItem)
                    }
                }
            } else {
                it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
        disposable = null
    }

    class SearchViewModelFactory(private val searchRepository: SearchRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(searchRepository) as T
        }
    }

}