package com.example.fastcampusadvanced.chapter2.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.example.fastcampusadvanced.chapter2.util.ViewUtil.showKeyboard

object ViewUtil {

    fun EditText.setOnEditorActionListener(action : Int, invoke: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->
            if (actionId == action) {
                invoke()
                true
            } else {
                false
            }
        }
    }

    fun EditText.showKeyboard() {
        this.requestFocus()
        val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun EditText.showKeyboardDelay() {
        postDelayed({
            showKeyboard()
        }, 200)
    }

    fun EditText.hideKeyboard() {
        this.clearFocus()
        val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }
}