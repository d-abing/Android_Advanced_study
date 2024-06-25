package com.example.fastcampusadvanced.chapter3

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.example.fastcampusadvanced.chapter3.model.DetailItem
import com.example.fastcampusadvanced.chapter3.model.Type
import com.example.fastcampusadvanced.databinding.ActivityDetailBinding
import java.util.Calendar

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private val adapter = DetailListAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initView()

        adapter.submitList(mockData())
    }

    private fun initView() {
        binding.cardTitleTv.text = intent.getStringExtra(CARD_NAME)
        binding.cardL.backgroundTintList = intent.getParcelableExtra(CARD_COLOR) as? ColorStateList
        binding.historyRv.adapter = adapter
    }

    private fun mockData(): List<DetailItem> {
        fun createDate(year: Int, month: Int, day: Int) = Calendar.getInstance().apply {
            set(year, month, day)
        }.time

        val list = mutableListOf<DetailItem>().apply {
            add(
                DetailItem(
                    1,
                    createDate(2024, 6, 25),
                    "A상점",
                    24000,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    2,
                    createDate(2024, 6, 25),
                    "B상점",
                    121200,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    3,
                    createDate(2024, 6, 24),
                    "온라인 마트",
                    11900,
                    Type.CANCEL
                )
            )
            add(
                DetailItem(
                    4,
                    createDate(2024, 6, 24),
                    "온라인 마트",
                    11900,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    5,
                    createDate(2024, 6, 23),
                    "대형 마트",
                    30200,
                    Type.POINT
                )
            )
            add(
                DetailItem(
                    6,
                    createDate(2024, 6, 23),
                    "대형 마트",
                    302000,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    7,
                    createDate(2024, 6, 21),
                    "창고 마트",
                    27000,
                    Type.CANCEL
                )
            )
            add(
                DetailItem(
                    8,
                    createDate(2024, 6, 21),
                    "창고 마트",
                    27000,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    9,
                    createDate(2024, 6, 17),
                    "주유소",
                    27000,
                    Type.PAY
                )
            )
            add(
                DetailItem(
                    10,
                    createDate(2024, 6, 13),
                    "음식점",
                    12000,
                    Type.PAY
                )
            )
        }

        return list
    }

    companion object {
        private const val CARD_NAME = "CARD_NAME"
        private const val CARD_COLOR = "CARD_COLOR"

        fun start(
            context: Context,
            cardName: String,
            cardColor: ColorStateList?,
            optionsCompat: ActivityOptionsCompat
        ) {
            Intent(context, DetailActivity::class.java).apply {
                putExtra(CARD_NAME, cardName)
                putExtra(CARD_COLOR, cardColor)
            }.run {
                context.startActivity(this, optionsCompat.toBundle())
            }
        }
    }
}