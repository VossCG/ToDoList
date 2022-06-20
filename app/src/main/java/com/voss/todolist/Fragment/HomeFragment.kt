package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.ArgsToContent
import com.voss.todolist.Adapter.HomeEventAdapter
import com.voss.todolist.R
import com.voss.todolist.Util.LinearItemDecoration
import com.voss.todolist.Util.dpToPx
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val mAdapter by lazy { HomeEventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = viewModel.calendar
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1

        setViewModelObserve(currentYear, currentMonth)
        setRecyclerView(currentYear, currentMonth)

        binding.homeTitleTv.setOnClickListener {
        }
    }

    private fun setViewModelObserve(year: Int, month: Int) {
        viewModel.readAllEvent.observe(viewLifecycleOwner) { allEvent ->
            val monthsList = allEvent.filter { event ->
                event.getYear() == year && event.getMonth() == month
            }
            mAdapter.submitList(monthsList)
        }
    }

    private fun setRecyclerView(year: Int, month: Int) {
        binding.homeRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 15.0f)))
        }

        mAdapter.itemOnClick = { adapterPosition ->
            val directions = HomeFragmentDirections.actionHomeFragmentToContentFragment(
                ArgsToContent(adapterPosition, year, month)
            )
            navController.navigate(directions)
        }
    }

}
