package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
import com.voss.todolist.util.disPlayToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private var isTitle: Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setListener()
        setRecyclerView()
    }

    private fun changeSearchFactor() {
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

    private fun setListener() {
        binding.filterFab.setPreventQuickerClick {
            changeSearchFactor()
        }
        binding.searchCancelBtn.backArrowBtn.setOnClickListener {
            navController.navigateUp()
        }
        binding.searchEdt.addTextChangedListener {
            viewModel.setKeyWord(binding.searchEdt.text.toString())
        }
    }

    private fun setObserver() {
        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            mAdapter.submitList(viewModel.getSearchEvent(viewModel.keyWord.value!!))
        }
        viewModel.keyWord.observe(viewLifecycleOwner) { keyWord ->
            mAdapter.submitList(viewModel.getSearchEvent(keyWord))
        }
    }

    private fun setRecyclerView() {
        setAdapter(mAdapter)
        binding.searchRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }
    }

    private fun setAdapter(adapter: SearChRecyclerAdapter) {
        adapter.apply {
            itemExpand = { clickPosition ->
                val layoutManager = binding.searchRcv.layoutManager as LinearLayoutManager
                val lastPositionVisible = layoutManager.findLastVisibleItemPosition()

                if (clickPosition == lastPositionVisible) {
                    layoutManager.scrollToPositionWithOffset(clickPosition, 0)
                }
            }
            itemDelete = { event ->
                viewModel.deleteEvent(event)
                showUndoSnackBar(event)
            }
            itemUpdate = { event ->
                val direction =
                    SearchFragmentDirections.actionSearchFragmentToUpdateEventFragment(event)
                navController.navigate(direction)
            }
        }
    }

    private fun showUndoSnackBar(event: Event) {
        Snackbar.make(binding.root, "已完成刪除", Snackbar.LENGTH_SHORT)
            .setAnchorView(binding.filterFab)
            .setAction("undo") {
                viewModel.addEvent(event)
                disPlayToastShort(requireContext(), "回復刪除")
            }.show()
    }
}
