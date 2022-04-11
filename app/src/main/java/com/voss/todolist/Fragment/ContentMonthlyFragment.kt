package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Adapter.ContentMonthlyAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.UpdateRecyclerData
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.ContentmonthlyfragmentBinding

class ContentMonthlyFragment : BaseFragment<ContentmonthlyfragmentBinding>(ContentmonthlyfragmentBinding::inflate) {

    private val callback = object : UpdateRecyclerData {
        override fun updateContentItem(data: EventTypes) {
            val direction = ContentMonthlyFragmentDirections.actionContentFragmentToUpdateEventFragment(data)
            navController.navigate(direction)
        }

        override fun deleteContentItem(data: EventTypes) {
            viewModel.deleteEvent(data)
        }
    }

    private val mAdapter: ContentMonthlyAdapter by lazy { ContentMonthlyAdapter(callback) }
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    val args: ContentMonthlyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView(args.contentArgs.position)
        setViewModel()

    }



    private fun setRecyclerView(position: Int) {
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
            mAdapter.setData(monthsList)
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