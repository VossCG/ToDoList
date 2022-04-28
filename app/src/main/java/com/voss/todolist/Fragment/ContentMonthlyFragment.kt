package com.voss.todolist.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Adapter.ContentListAdapter
import com.voss.todolist.Adapter.ContentMonthlyAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.UpdateRecyclerData
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.ContentmonthlyfragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class ContentMonthlyFragment : BaseFragment<ContentmonthlyfragmentBinding>(ContentmonthlyfragmentBinding::inflate) {

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
        }
    }

    private fun setViewModelObserve() {
        viewModel.readAllEvent.observe(this) {
            val monthsList = it.filter { (it.year == args.contentArgs.year && it.month == args.contentArgs.months) }
            mAdapter.submitList(monthsList)
        }
    }
}