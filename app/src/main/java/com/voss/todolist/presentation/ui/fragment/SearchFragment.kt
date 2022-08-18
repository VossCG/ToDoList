package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.R
import com.voss.todolist.presentation.ui.adapter.SearChRecyclerAdapter
import com.voss.todolist.util.setPreventQuickerClick
import com.voss.todolist.databinding.FragmentSearchBinding
import com.voss.todolist.presentation.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private var isTitle: Boolean = true
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setObserver()
        setRecyclerView()

    }

    private fun setListener() {
        binding.filterFab.setPreventQuickerClick {
            changeSearchFactor()
        }
        binding.searchTb.setNavigationOnClickListener {
            navController.navigateUp()
        }
        binding.searchEdt.addTextChangedListener {
            viewModel.setKeyWord(binding.searchEdt.text.toString())
        }
    }

    private fun setObserver() {
        viewModel.keyWord.observe(viewLifecycleOwner) { keyWord ->

            // filter flow & emit value to searchState
            // viewModel.setUiEvent(keyWord)

            // this a cold flow - use flow
            lifecycleScope.launchWhenStarted {
                viewModel.getSearchEventFlow(keyWord).collectLatest {
                    mAdapter.submitList(it)
                }
            }
        }

        viewModel.filterFactor.observe(viewLifecycleOwner) { factor ->
            when (factor) {
                "title" -> {
                    binding.filterFab.setImageResource(R.drawable.ic_baseline_title_24)
                    Toast.makeText(requireContext(), "Search Title", Toast.LENGTH_SHORT).show()
                }
                "content" -> {
                    binding.filterFab.setImageResource(R.drawable.ic_baseline_content_paste_24)
                    Toast.makeText(requireContext(), "Search Content", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // this is a hot flow - stateFlow
//        searchJob = lifecycleScope.launchWhenStarted {
//            viewModel.searchEventState.collectLatest { events ->
//                // collect when stateflow change
//                mAdapter.submitList(events)
//                Log.d("SearchFragment","consume collect, adapter.submitList($events)")
//            }
//            Log.d("SearchFragment","searchJob finish")
//        }
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
                scrollToExpanded(clickPosition)
            }
            itemDelete = { event ->
                viewModel.deleteEvent(event)
            }
            itemUpdate = { event ->
                val direction =
                    SearchFragmentDirections.actionSearchFragmentToUpdateEventFragment(event)
                navController.navigate(direction)
            }
        }
    }

    private fun scrollToExpanded(clickPosition: Int) {
        val layoutManager = binding.searchRcv.layoutManager as LinearLayoutManager
        val lastPositionVisible = layoutManager.findLastVisibleItemPosition()

        if (clickPosition == lastPositionVisible) {
            layoutManager.scrollToPositionWithOffset(clickPosition, 0)
        }
    }
    private fun changeSearchFactor() {
        isTitle = !isTitle
        if (isTitle) {
            viewModel.setSearchFactor("title")
        } else {
            viewModel.setSearchFactor("content")
        }
    }

    override fun onDestroy() {
        searchJob = null
        super.onDestroy()
    }
}
