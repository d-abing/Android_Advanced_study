package com.example.fastcampusadvanced.chapter5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.fastcampusadvanced.chapter5.list.ListAdapter
import com.example.fastcampusadvanced.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private var binding: FragmentFavoriteBinding? = null
    private val adapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFavoriteBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            recyclerView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.apply {
            if (Common.favoriteList.isEmpty()) {
                emptyTv.isVisible = true
                recyclerView.isVisible = false
            } else {
                emptyTv.isVisible = false
                recyclerView.isVisible = true
            }
        }
        adapter.submitList(Common.favoriteList.sortedBy { it.dateTime })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}