package com.voss.todolist.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.voss.todolist.Adapter.ArgsToContent
import com.voss.todolist.Adapter.HomeEventAdapter
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.HomefragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Runnable
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomefragmentBinding>(HomefragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    private val currentMonth: Int by lazy { calendar.get(Calendar.YEAR) }
    private val currentYear: Int by lazy { calendar.get(Calendar.MONTH) }
    private val mAdapter by lazy { HomeEventAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewModelObserve()
        setRecyclerView()
    }

    private fun setViewModelObserve() {
        viewModel.readAllEvent.observe(this) {
            val monthsList = it.filter {
                it.year == currentYear && it.month == currentMonth
            }
            mAdapter.setData(monthsList)
        }
    }

    private fun setRecyclerView() {
        binding.homeRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = mAdapter
        }

        mAdapter.itemOnClick = { adapterPosition ->

            val directions = HomeFragmentDirections.actionHomeFragmentToContentFragment(
                ArgsToContent(adapterPosition, currentYear, currentMonth))

            navController.navigate(directions)
        }
    }

}
