package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.ArgsToContent
import com.voss.todolist.Adapter.HomeEventAdapter
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
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    private val currentYear: Int by lazy { calendar.get(Calendar.YEAR) }
    private val currentMonth: Int by lazy { calendar.get(Calendar.MONTH) }
    private val mAdapter by lazy { HomeEventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViewModelObserve()
        setRecyclerView()
    }

    private fun setViewModelObserve() {
        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            val monthsList = it.filter {
                it.year == currentYear && it.month == currentMonth
            }
            mAdapter.submitList(monthsList)
        }
    }

    private fun setRecyclerView() {
        binding.homeRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(), 15.0f)))
        }

        mAdapter.itemOnClick = { adapterPosition ->

            val directions = HomeFragmentDirections.actionHomeFragmentToContentFragment(
                ArgsToContent(adapterPosition, currentYear, currentMonth))

            navController.navigate(directions)
        }
    }

}
