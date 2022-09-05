package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.R
import com.voss.todolist.util.setPreventQuickerClick
import com.voss.todolist.databinding.FragmentSearchBinding
import com.voss.todolist.presentation.ui.adapter.SearchViewAdapter
import com.voss.todolist.presentation.viewModel.SearchFactor
import com.voss.todolist.presentation.viewModel.SearchViewModel
import com.voss.todolist.util.LinearItemDecoration
import com.voss.todolist.util.displayToastShort
import com.voss.todolist.util.dpToPx
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val mAdapter: SearchViewAdapter by lazy { SearchViewAdapter() }
    private var isTitle: Boolean = true
    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListener()
        setObserver()
        setRecyclerView()

    }

    override fun onStart() {
        setObserver()
        super.onStart()
    }

    private fun setListener() {
        binding.filterFab.setPreventQuickerClick {
            changeSearchFactor()
        }
        binding.searchTb.setNavigationOnClickListener {
            navController.navigateUp()
        }
        binding.searchEdt.addTextChangedListener {
            viewModel.setSearchStateKeyWord(binding.searchEdt.text.toString())
        }
    }

    private fun setObserver() {
        searchJob = lifecycleScope.launchWhenStarted {
            launch {
                viewModel.keyWordState.collectLatest { keyWord ->
                    viewModel.setEventState(keyWord)
                }
            }
            launch {
                viewModel.factorState.collectLatest { factor ->
                    when (factor) {
                        SearchFactor.Title -> {
                            binding.filterFab.setImageResource(R.drawable.ic_baseline_title_24)
                            displayToastShort(requireContext(), "Search Title")
                        }
                        SearchFactor.Content -> {
                            binding.filterFab.setImageResource(R.drawable.ic_baseline_content_paste_24)
                            displayToastShort(requireContext(), "Search Content")
                        }
                    }
                }
            }
            launch {
                viewModel.eventState.collectLatest { events ->
                    mAdapter.submitList(events)
                }
            }
        }
    }

    private fun setRecyclerView() {
        setAdapter(mAdapter)
        binding.searchRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 10f)))
            adapter = mAdapter
        }
    }

    private fun setAdapter(adapter: SearchViewAdapter) {
        adapter.apply {
            itemClickExpand = { clickPosition -> scrollToExpanded(clickPosition) }
            itemClickDelete = { event -> viewModel.deleteEvent(event) }
            itemClickUpdate = { event ->
                val direction = SearchFragmentDirections.actionSearchFragmentToUpdateEventFragment(event)
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
            viewModel.setSearchStateFactor(SearchFactor.Title)
        } else {
            viewModel.setSearchStateFactor(SearchFactor.Content)
        }
    }

    override fun onStop() {
        searchJob?.cancel()
        searchJob = null
        super.onStop()
    }
}
