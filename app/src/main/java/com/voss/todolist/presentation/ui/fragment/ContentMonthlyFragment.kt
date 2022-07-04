package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.presentation.ui.adapter.ContentListAdapter
import com.voss.todolist.R
import com.voss.todolist.util.LinearItemDecoration
import com.voss.todolist.util.dpToPx
import com.voss.todolist.presentation.viewModel.EventViewModel
import com.voss.todolist.databinding.FragmentContentmonthlyBinding
import com.voss.todolist.presentation.ui.adapter.ArgsToContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentMonthlyFragment :
    BaseFragment<FragmentContentmonthlyBinding>(FragmentContentmonthlyBinding::inflate) {

    private val mAdapter: ContentListAdapter by lazy { ContentListAdapter() }
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val args: ContentMonthlyFragmentArgs by navArgs()
    private lateinit var contentArgs: ArgsToContent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(args.contentArgs.position)

        contentArgs = args.contentArgs

        setViewModelObserve()

        // Item OnClick callBack
        mAdapter.itemClickUpdate = {
            val direction =
                ContentMonthlyFragmentDirections.actionContentFragmentToUpdateEventFragment(it)
            navController.navigate(direction)
        }
        mAdapter.itemClickDelete = {
            viewModel.deleteEvent(it)
        }
        binding.contentAddFab.setOnClickListener {
            navController.navigate(R.id.action_contentFragment_to_editEventFragment)
        }
    }

    private fun setRecyclerView(position: Int) {
        binding.contentRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            scrollToPosition(position)
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 10f)))
        }
    }

    private fun setViewModelObserve() {
        viewModel.readAllEvent.observe(viewLifecycleOwner) { event ->
            val monthsList = event.filter {
                (it.getYear() == contentArgs.year && it.getMonth() == contentArgs.months)
            }
            mAdapter.submitList(monthsList)
        }
    }
}