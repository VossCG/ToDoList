package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.BrowseRecyclerAdapter
import com.voss.todolist.databinding.BrowsefragmentBinding

class BrowseFragment : BaseFragment<BrowsefragmentBinding>(BrowsefragmentBinding::inflate) {
    // 先使用假資料代替，之後替換成 從資料庫拿出一個年份的list
    // 只顯示有寫入事件的year，沒寫入的 不顯示在當中
    private val yearList = listOf<Int>(
        2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030
    )
    private lateinit var mAdapter: BrowseRecyclerAdapter
    private val navController : NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = BrowseRecyclerAdapter(yearList,navController)
        binding.yearRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BrowseFragment.context)
            adapter = mAdapter
        }
    }

}



