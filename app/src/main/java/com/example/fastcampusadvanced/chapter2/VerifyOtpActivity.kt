package com.example.fastcampusadvanced.chapter2

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import com.example.fastcampusadvanced.R
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.showKeyboardDelay
import com.example.fastcampusadvanced.databinding.ActivityVerifyOtpBinding
import com.google.android.gms.auth.api.phone.SmsRetriever

class VerifyOtpActivity : AppCompatActivity(), AuthOtpReceiver.OtpReceiveListener {
    private lateinit var binding: ActivityVerifyOtpBinding

    private var smsReceiver: AuthOtpReceiver? = null

    private var timer: CountDownTimer? = object : CountDownTimer(3 * 60 * 1000, 1000) {
        override fun onTick(p0: Long) {
            val min = (p0 / 1000) / 60
            val sec = (p0 / 1000) % 60

            binding.timerTv.text = "$min:${String.format("%02d", sec)}"
        }

        override fun onFinish() {
            binding.timerTv.text = ""
            Toast.makeText(this@VerifyOtpActivity, "인증시간이 초과되었습니다.\n 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.otpCodeEt.showKeyboardDelay()
        startSmsReceiver()
    }

    override fun onDestroy() {
        clearTimer()
        stopSmsReceiver()
        super.onDestroy()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
    }

    private fun initView() {
        startTimer()
        with(binding) {
            otpCodeEt.doAfterTextChanged {
                if ( otpCodeEt.length() >= 6) {
                    stopTimer()
                    Toast.makeText(this@VerifyOtpActivity, "인증번호 입력이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startTimer() {
        timer?.start()
    }

    private fun stopTimer() {
        timer?.cancel()
    }

    private fun clearTimer() {
        stopTimer()
        timer = null
    }

    private fun startSmsReceiver() {
        SmsRetriever.getClient(this).startSmsRetriever().also { task ->
            task.addOnSuccessListener {
                if (smsReceiver == null) {
                    smsReceiver = AuthOtpReceiver().apply {
                        setOtpListener(this@VerifyOtpActivity)
                    }
                }
                registerReceiver(smsReceiver, smsReceiver!!.doFilter())
            }

            task.addOnFailureListener {
                stopSmsReceiver()
            }
        }
    }

    private fun stopSmsReceiver() {
        if (smsReceiver != null) {
            unregisterReceiver(smsReceiver)
            smsReceiver = null
        }
    }

    override fun onOtpReceived(otp: String) {
        binding.otpCodeEt.setText(otp)
    }
}