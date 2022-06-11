package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.voss.todolist.Adapter.BrowseExpandableListAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.FragmentMonthBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MonthFragment : BaseFragment<FragmentMonthBinding>(FragmentMonthBinding::inflate) {
    private val args: MonthFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }
    private val viewModel: EventViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.monthTopTitleTv.text = "${args.year}  Months"
        setExpandableListView()
    }

    private fun setExpandableListView() {
        val data = viewModel.readAllEvent.value ?: emptyList()
        val listByMonths = getTwoDimensionMonthsList(data)

        val mAdapter = BrowseExpandableListAdapter(requireContext(), listByMonths, navController)
        binding.monthExpandableListView.setAdapter(mAdapter)

        // 判斷點擊 groupItem 月份 裡面是否有child ，若沒有則用Toast顯示 並回傳true讓它無法展開
        binding.monthExpandableListView.setOnGroupClickListener { _, _, groupPosition, _ ->
            val event = listByMonths[groupPosition]
            if (event.isEmpty()) {
                Toast.makeText(
                    this.context,
                    "${mAdapter.getGroup(groupPosition)} 沒有任何行程",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnGroupClickListener true
            } else
                return@setOnGroupClickListener false
        }
    }

    private fun getTwoDimensionMonthsList(data: List<EventTypes>): List<List<EventTypes>> {
        val list = mutableListOf<List<EventTypes>>()
        for (i in 0..11) {
            list.add(data.filter { it.month == i && it.year == args.year })
        }
        return list

    }

}