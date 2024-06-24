package com.example.fastcampusadvanced.chapter2

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.example.fastcampusadvanced.R
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.hideKeyboard
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.setOnEditorActionListener
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.showKeyboard
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.showKeyboardDelay
import com.example.fastcampusadvanced.databinding.ActivityIdentityInputBinding

class IdentityInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIdentityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIdentityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        initView()
        binding.nameEt.showKeyboardDelay()
    }
    
    private fun initView() {
        with(binding) {
            nameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT)  {
                if (validName()) {
                    nameL.error = null
                    if (birthdayL.isVisible) {
                        confirmBtn.isVisible = true
                    } else {
                        birthdayL.isVisible = true
                        birthdayEt.showKeyboard()
                    }
                } else {
                    birthdayL.isVisible = false
                    nameL.error = "이름을 확인해주세요"
                }
            }

            birthdayEt.doAfterTextChanged {
                if (birthdayEt.length() > 7) {
                    if (validBirthday()) {
                        birthdayL.error = null
                        if (phoneL.isVisible) {
                            confirmBtn.isVisible = true
                        } else {
                            genderL.isVisible = true
                            birthdayEt.hideKeyboard()
                        }
                    } else {
                        confirmBtn.isVisible = false
                        birthdayL.error = "생년월일을 확인해주세요."
                    }
                }
            }

            birthdayEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                if (validBirthday() && birthdayEt.length() > 7) {
                    confirmBtn.isVisible = phoneL.isVisible
                    birthdayL.error = null
                } else {
                    birthdayL.error = "생년월일을 확인해주세요."
                }
                birthdayEt.hideKeyboard()
            }

            genderCg.setOnCheckedStateChangeListener { _, _ ->
                if (!telecomL.isVisible) {
                    telecomL.isVisible = true
                }
            }

            telecomCg.setOnCheckedStateChangeListener { _, _ ->
                if (!phoneL.isVisible) {
                    phoneL.isVisible = true
                    phoneEt.showKeyboard()
                }
            }

            phoneEt.doAfterTextChanged {
                if (phoneEt.length() > 10) {
                    if (validPhone()) {
                        phoneL.error = null
                        confirmBtn.isVisible = true
                        phoneEt.hideKeyboard()
                    } else {
                        confirmBtn.isVisible = false
                        phoneL.error = "휴대폰 번호를 확인해주세요."
                    }
                }
            }

            phoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                confirmBtn.isVisible = phoneEt.length() > 9 && validPhone()
                phoneEt.hideKeyboard()
            }
        }
    }

    fun onClickDone() {
        if (!validName()) {
            binding.nameL.error = "이름을 확인해주세요."
            return
        }

        if (!validBirthday()) {
            binding.birthdayL.error = "생년월일을 확인해주세요."
            return
        }

        if (!validPhone()) {
            binding.phoneL.error = "휴대폰 번호를 확인해주세요."
            return
        }

        startActivity(Intent(this, VerifyOtpActivity::class.java))
    }

    private fun validName() = !binding.nameEt.text.isNullOrEmpty()
            && REGEX_NAME.toRegex().matches(binding.nameEt.text!!)

    private fun validBirthday() = !binding.birthdayEt.text.isNullOrEmpty()
            && REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEt.text!!)

    private fun validPhone() = !binding.phoneEt.text.isNullOrEmpty()
            && REGEX_PHONE.toRegex().matches(binding.phoneEt.text!!)
    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_BIRTHDAY = "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$"
        private const val REGEX_PHONE = "^010([0-9]{3,4})([0-9]{4})$"
    }
}