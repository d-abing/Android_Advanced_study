package com.example.fastcampusadvanced.chapter2.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.view.children
import com.example.fastcampusadvanced.R
import com.example.fastcampusadvanced.databinding.WidgetShuffleNumberKeyboardBinding
import kotlin.random.Random

class ShuffleNumberKeyboard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : GridLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var _binding: WidgetShuffleNumberKeyboardBinding? = null
    private val binding get() = _binding!!

    private var listener: KeyPadListener? = null

    init {
        _binding = WidgetShuffleNumberKeyboardBinding.inflate(LayoutInflater.from(context), this, true)
        binding.view = this
        binding.clickListener = this
        shuffle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        _binding = null
    }

    fun shuffle() {
        val keyNumberArray = ArrayList<String>()
        for (i in 0..9) {
            keyNumberArray.add(i.toString())
        }

        binding.gridL.children.forEach { view ->
            if (view is TextView && view.tag != null) {
                val randIndex = Random.nextInt(keyNumberArray.size)
                view.text = keyNumberArray[randIndex]
                keyNumberArray.removeAt(randIndex)
            }
        }
    }

    fun setKeyPadListener(keyPadListener: KeyPadListener) {
        this.listener = keyPadListener
    }

    fun onClickDelete() {
        listener?.onClickDelete()
    }

    fun onClickDone() {
        listener?.onClickDone()
    }

    interface KeyPadListener {
        fun onClickNum(num: String)
        fun onClickDelete()
        fun onClickDone()

    }

    override fun onClick(v: View?) {
        if (v is TextView && v.tag != null) {
            listener?.onClickNum(v.text.toString())
        }
    }
}