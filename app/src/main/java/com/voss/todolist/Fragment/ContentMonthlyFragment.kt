package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.ContentListAdapter
import com.voss.todolist.R
import com.voss.todolist.Util.LinearItemDecoration
import com.voss.todolist.Util.dpToPx
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.FragmentContentmonthlyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContentMonthlyFragment : BaseFragment<FragmentContentmonthlyBinding>(FragmentContentmonthlyBinding::inflate) {

    private val mAdapter: ContentListAdapter by lazy { ContentListAdapter() }
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    val args: ContentMonthlyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(args.contentArgs.position)

        setViewModelObserve()

        // Item OnClick callBack
        mAdapter.itemClickUpdate = {
            val direction = ContentMonthlyFragmentDirections.actionContentFragmentToUpdateEventFragment(it)
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
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(),10f)))
        }
    }

    private fun setViewModelObserve() {
        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            val monthsList = it.filter { (it.getYear() == args.contentArgs.year && it.getMonth() == args.contentArgs.months) }
            mAdapter.submitList(monthsList)
        }
    }
}