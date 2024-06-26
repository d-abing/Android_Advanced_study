package com.example.fastcampusadvanced.chapter4

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fastcampusadvanced.chapter4.mvc.view_controller.MVCActivity
import com.example.fastcampusadvanced.chapter4.mvi.view.MVIActivity
import com.example.fastcampusadvanced.chapter4.mvp.view.MVPActivity
import com.example.fastcampusadvanced.chapter4.mvvm.view.MVVMActivity
import com.example.fastcampusadvanced.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.view = this
        }

    }

    fun openMVC() {
        startActivity(Intent(this, MVCActivity::class.java))
    }

    fun openMVP() {
        startActivity(Intent(this, MVPActivity::class.java))
    }

    fun openMVVM() {
        startActivity(Intent(this, MVVMActivity::class.java))
    }

    fun openMVI() {
        startActivity(Intent(this, MVIActivity::class.java))
    }


}