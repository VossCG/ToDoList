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
class ContentMonthlyFragment :
    BaseFragment<ContentmonthlyfragmentBinding>(ContentmonthlyfragmentBinding::inflate) {

    private val callback = object : UpdateRecyclerData {
        override fun updateContentItem(data: EventTypes) {
            val direction = ContentMonthlyFragmentDirections.actionContentFragmentToUpdateEventFragment(data)
            navController.navigate(direction)
        }

        override fun deleteContentItem(data: EventTypes) {
            viewModel.deleteEvent(data)
        }
    }

    private val mAdapter: ContentListAdapter by lazy { ContentListAdapter(callback) }
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    val args: ContentMonthlyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(args.contentArgs.position)
        setViewModel()

        binding.contentAddFab.setOnClickListener {
            navController.navigate(R.id.action_contentFragment_to_editEventFragment)
        }

    }

    private fun setRecyclerView(position: Int) {
        Timber.d("now:$position")
        val recyclerView = binding.contentRecyclerview
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            scrollToPosition(position)
        }
    }

    private fun setViewModel() {
        viewModel.readAllEvent.observe(this) {
            val monthsList = it.filter {
                (it.year == args.contentArgs.year && it.month == args.contentArgs.months)
            }
            Log.d("Content:","list:${monthsList}")
            mAdapter.submitList(monthsList)
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun onStart() {
        super.onStart()
        activity?.findViewById<BottomNavigationView>(R.id.nav_bottom)?.visibility = View.GONE

    }

    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.nav_bottom)?.visibility = View.VISIBLE

    }


}