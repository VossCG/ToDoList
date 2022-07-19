package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.data.args.EditArgs
import com.voss.todolist.presentation.ui.adapter.EventCardListAdapter
import com.voss.todolist.util.LinearItemDecoration
import com.voss.todolist.util.dpToPx
import com.voss.todolist.presentation.viewModel.EventCardViewModel
import com.voss.todolist.databinding.FragmentEventcardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventCardFragment :
    BaseFragment<FragmentEventcardBinding>(FragmentEventcardBinding::inflate) {

    private val mAdapter: EventCardListAdapter by lazy { EventCardListAdapter() }
    private val viewModel: EventCardViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private val args: EventCardFragmentArgs by navArgs()
    private val contentArgs get() = args.contentArgs
    private lateinit var date: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setClickListener()

        initAttributes()
        initView()
    }

    private fun initAttributes() {
        date = viewModel.getDateFormat(contentArgs.year, contentArgs.month, contentArgs.day)
    }

    private fun initView() {
        binding.eventCardDateTv.text = date
        setRecyclerView(args.contentArgs.position)
    }

    private fun setClickListener() {
        binding.eventCardAddEventFab.setOnClickListener {
            val direction = EventCardFragmentDirections.actionEventCardFragmentToEditEventFragment(
                EditArgs(contentArgs.year, contentArgs.month, contentArgs.day)
            )
            navController.navigate(direction)
        }
    }

    private fun setRecyclerView(position: Int) {
        binding.eventCardRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            scrollToPosition(position)
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 10f)))
        }
        // Item OnClick callBack
        mAdapter.itemClickUpdate = { event ->
            val direction =
                EventCardFragmentDirections.actionEventCardFragmentToUpdateEventFragment(event)
            navController.navigate(direction)
        }
        mAdapter.itemClickDelete = { event ->
            viewModel.deleteEvent(event)
        }
        mAdapter.submitList(viewModel.getSingleDayEvent(date))
    }

    private fun setObserver() {
        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            mAdapter.submitList(viewModel.getSingleDayEvent(date))
        }
    }
}