package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Adapter.ExpandableListViewAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.MonthfragmentBinding

class MonthFragment : BaseFragment<MonthfragmentBinding>(MonthfragmentBinding::inflate) {
    private val args: MonthFragmentArgs by navArgs()
    private val navController:NavController by lazy { findNavController() }
    private val viewModel: EventViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.topTitleTv.text = "${args.year}  Months"
        setExpandableListView()
    }

    private fun setExpandableListView() {
        val data = viewModel.readAllEvent.value ?: emptyList()
        val listByMonths = getTwoDimensionMonthsList(data)

        val mAdapter = ExpandableListViewAdapter(this.context!!, listByMonths,navController)
        binding.monthsExpandableListView.setAdapter(mAdapter)
    }

    private fun getTwoDimensionMonthsList(data: List<EventTypes>): List<List<EventTypes>> {
        val list = mutableListOf<List<EventTypes>>()
        for (i in 0..11) {
            list.add(data.filter { it.month == i && it.year == args.year })
        }
        return list

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