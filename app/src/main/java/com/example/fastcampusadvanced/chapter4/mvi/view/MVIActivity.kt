package com.example.fastcampusadvanced.chapter4.mvi.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.fastcampusadvanced.chapter4.mvi.intent.MVIIntent
import com.example.fastcampusadvanced.chapter4.mvi.intent.MVIState
import com.example.fastcampusadvanced.chapter4.mvi.intent.MVIViewModel
import com.example.fastcampusadvanced.chapter4.mvi.model.repository.ImageRepositoryImpl
import com.example.fastcampusadvanced.databinding.ActivityMviBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MVIActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMviBinding
    private val viewModel: MVIViewModel by viewModels {
        MVIViewModel.MVIViewModelFactory(ImageRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMviBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

        observeViewModel()
    }

    fun loadImage() {
        lifecycleScope.launch {
            viewModel.channel.send(MVIIntent.LoadImage)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                when (state) {
                    is MVIState.Idle -> {
                        binding.progressView.isVisible = false
                    }

                    is MVIState.Loading -> {
                        binding.progressView.isVisible = true
                    }

                    is MVIState.LoadedImage -> {
                        binding.progressView.isVisible = false
                        binding.randomIv.run {
                            setBackgroundColor(Color.parseColor(state.image.color))
                            load(state.image.url) {
                                crossfade(300)
                            }
                        }
                        binding.imageCountTv.text = "불러온 이미지 수 : ${state.count}"
                    }
                }
            }
        }
    }
}