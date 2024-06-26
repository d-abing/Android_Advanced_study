package com.example.fastcampusadvanced.chapter4.mvvm.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.fastcampusadvanced.chapter4.mvvm.model.repository.ImageRepositoryImpl
import com.example.fastcampusadvanced.chapter4.mvvm.viewmodel.MvvmViewModel
import com.example.fastcampusadvanced.databinding.ActivityMvvmBinding

class MVVMActivity : AppCompatActivity() {
    // View
    // ViewModel과 데이터를 바인딩하여 UI를 업데이트합니다.

    private val viewModel: MvvmViewModel by viewModels {
        MvvmViewModel.MvvmViewModelFactory(ImageRepositoryImpl())
    }

    private lateinit var binding: ActivityMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }
    }
}