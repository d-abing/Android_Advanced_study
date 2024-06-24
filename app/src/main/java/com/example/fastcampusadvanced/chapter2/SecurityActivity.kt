package com.example.fastcampusadvanced.chapter2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.fastcampusadvanced.chapter2.util.AppSignatureHelper
import com.example.fastcampusadvanced.databinding.ActivitySecurityBinding

class SecurityActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecurityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecurityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this

        AppSignatureHelper(this).apply {
            Log.d("hash", "hash : ${appSignature}")
        }

        /*
        * <#> [Sample] 본인확인 인증번호 [123456] 입니다. hash값
        * */
    }

    fun openShuffle() {
        startActivity(Intent(this, PinActivity::class.java))
    }

    fun openVerifyOtp() {
        startActivity(Intent(this, IdentityInputActivity::class.java))
    }
}