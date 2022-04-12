package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.SearChRecyclerAdapter
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.SearchfragmentBinding
import java.util.*

class SearchFragment : BaseFragment<SearchfragmentBinding>(SearchfragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val calendar by lazy { Calendar.getInstance() }
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }

        viewModel.readAllEvent.observe(this) {
            val monthsList = it.filter {
                it.year == calendar.get(Calendar.YEAR) && it.month == calendar.get(Calendar.MONTH)
            }
            mAdapter.setData(monthsList)
        }

    }
}