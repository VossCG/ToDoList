package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.presentation.ui.adapter.SearChRecyclerAdapter
import com.voss.todolist.util.setPreventQuickerClick
import com.voss.todolist.databinding.FragmentSearchBinding
import com.voss.todolist.presentation.viewModel.SearchViewModel
import com.voss.todolist.util.closeKeyboard
import com.voss.todolist.util.setToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private var inputData: String = "null"
    private var isTitle: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            mAdapter.submitList(viewModel.filterEventsWithFactor(inputData))
        }
        setClickListener()
        setRecyclerView()
        setSearchEditText(view)

    }

    private fun setSearchEditText(view: View) {
        binding.searChEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputData = binding.searChEditText.text.toString()
                if (inputData.isNotEmpty()) {
                    // 關閉鍵盤
                    closeKeyboard(view, requireActivity())
                    // 獲得關鍵字過濾資料，放入adapter
                    val filterData = viewModel.filterEventsWithFactor(inputData)
                    if (filterData.isEmpty())
                        Toast.makeText(this.context, "搜尋條件 找不到相關資料 請重新查詢", Toast.LENGTH_SHORT)
                            .show()
                    else mAdapter.submitList(filterData)
                } else Toast.makeText(
                    this.context,
                    "Please enter title to SearCh",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setClickListener() {
        binding.filterFab.setPreventQuickerClick {
            switchSearchFactor()
        }
        binding.cancelSearchBut.backArrowBut.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun switchSearchFactor() {
        isTitle = !isTitle
        if (isTitle) {
            viewModel.setSearchFactor("title")
            binding.filterFab.setImageResource(R.drawable.ic_baseline_title_24)
            Toast.makeText(requireContext(), "Search Title", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.setSearchFactor("content")
            binding.filterFab.setImageResource(R.drawable.ic_baseline_content_paste_24)
            Toast.makeText(requireContext(), "Search Content", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setRecyclerView() {
        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }
        mAdapter.apply {
            itemClick = { clickPosition ->
                val layoutManager = binding.searchRecycler.layoutManager as LinearLayoutManager
                val lastPositionVisible = layoutManager.findLastVisibleItemPosition()

                if (clickPosition == lastPositionVisible) {
                    layoutManager.scrollToPositionWithOffset(clickPosition, 0)
                }
            }
            itemDelete = { event ->
                deleteEvent(event)
            }
            itemUpdate = { event ->
                val direction =
                    SearchFragmentDirections.actionSearchFragmentToUpdateEventFragment(event)
                navController.navigate(direction)
            }
        }
    }

    private fun deleteEvent(event: Event) {
        viewModel.deleteEvent(event)
        Snackbar.make(binding.root, "已完成刪除", Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.filterFab)
            .setAction("undo") {
                viewModel.addEvent(event)
                setToastShort(requireContext(), "回復刪除")
            }
            .show()
    }
}
