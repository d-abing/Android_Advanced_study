package com.example.fastcampusadvanced.chapter3

import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fastcampusadvanced.R
import com.example.fastcampusadvanced.databinding.ActivityWalletBinding
import com.example.fastcampusadvanced.generated.callback.OnClickListener

class WalletActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWalletBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.thirdCard.setOnClickListener(getCardClickListener(R.id.third_card_on_top))
        binding.secondCard.setOnClickListener(getCardClickListener(R.id.second_card_on_top))
        binding.firstCard.setOnClickListener(getCardClickListener(R.id.first_card_on_top))
    }

    private fun getCardClickListener(@IdRes endStateId: Int) = View.OnClickListener {
        with(binding) {
            when (root.currentState) {
                R.id.fan_out -> {
                    root.setTransition(R.id.fan_out, R.id.first_card_on_top)
                    root.transitionToEnd()
                    collapsedCardCompletedListener(endStateId)
                }

                R.id.third_card_on_top -> {
                    if (R.id.third_card_on_top == endStateId) {
                        openDetail(thirdCard, thirdCardTitleTv.text)
                    } else {
                        root.setTransition(R.id.third_card_on_top, endStateId)
                        root.transitionToEnd()
                    }
                }

                R.id.second_card_on_top -> {
                    if (R.id.second_card_on_top == endStateId) {
                        openDetail(secondCard, secondCardTitleTv.text)
                    } else {
                        root.setTransition(R.id.second_card_on_top, endStateId)
                        root.transitionToEnd()
                    }
                }

                R.id.first_card_on_top -> {
                    if (R.id.first_card_on_top == endStateId) {
                        openDetail(firstCard, firstCardTitleTv.text)
                    } else {
                        root.setTransition(R.id.first_card_on_top, endStateId)
                        root.transitionToEnd()
                    }
                }
            }
        }
    }

    private fun openDetail(view: View, cardName: CharSequence) {
        view.transitionName = "card"
        val optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair(view, view.transitionName))
        DetailActivity.start(this, cardName.toString(), view.backgroundTintList, optionsCompat)
    }

    private fun collapsedCardCompletedListener(@IdRes endStateId: Int) {
        with(binding) {
            root.setTransitionListener(object : TransitionAdapter() {
                override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                    if (currentId == R.id.first_card_on_top) {
                        root.setTransition(R.id.first_card_on_top, endStateId)
                        root.transitionToEnd()
                    }
                    root.setTransitionListener(null)
                }
            })
        }
    }
}