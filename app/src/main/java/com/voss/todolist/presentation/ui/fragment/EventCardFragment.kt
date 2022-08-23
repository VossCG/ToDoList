package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.presentation.ui.adapter.EventCardAdapter
import com.voss.todolist.util.LinearItemDecoration
import com.voss.todolist.util.dpToPx
import com.voss.todolist.presentation.viewModel.EventCardViewModel
import com.voss.todolist.databinding.FragmentEventcardBinding
import com.voss.todolist.util.displayToastShort
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class EventCardFragment :
    BaseFragment<FragmentEventcardBinding>(FragmentEventcardBinding::inflate) {

    private val mAdapter: EventCardAdapter by lazy { EventCardAdapter() }
    private val viewModel: EventCardViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private val args: EventCardFragmentArgs by navArgs()
    private val contentArgs get() = args.contentArgs
    private var eventFlowJob: Job? = null
    private lateinit var date: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAttributes()
        initView()

        setClickListener()
        subscribeToObserver()
    }

    private fun initAttributes() {
        date = viewModel.getDateFormat(contentArgs.year, contentArgs.month, contentArgs.day)
        viewModel.setUiStateEvent(date)
    }

    private fun initView() {
        binding.eventCardDateTb.title = date
        setRecyclerView(args.contentArgs.position)
    }

    private fun setClickListener() {
        binding.eventCardDateTb.setOnClickListener {
            navController.navigateUp()
        }
    }

    private fun setRecyclerView(position: Int) {
        setEventCardAdapter()
        binding.eventCardRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            scrollToPosition(position)
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 10f)))
        }
    }

    private fun setEventCardAdapter() {
        mAdapter.itemClickUpdate = { event ->
            val direction =
                EventCardFragmentDirections.actionEventCardFragmentToUpdateEventFragment(event)
            navController.navigate(direction)
        }
        mAdapter.itemClickDelete = { event ->
            viewModel.deleteEvent(event)
            showUndoSnackBar(event)
        }
    }

    private fun showUndoSnackBar(event: Event) {
        Snackbar.make(binding.root, "已完成刪除", Snackbar.LENGTH_SHORT)
            .setAction("undo") {
                viewModel.addEvent(event)
                displayToastShort(requireContext(), "回復刪除")
            }
            .show()
    }

    private fun subscribeToObserver() {
        eventFlowJob = lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { events ->
                mAdapter.submitList(events)
            }
        }
    }

    override fun onStop() {
        eventFlowJob?.cancel()
        super.onStop()
    }
}