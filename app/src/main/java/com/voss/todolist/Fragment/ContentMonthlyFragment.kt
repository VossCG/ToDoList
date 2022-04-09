package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Adapter.ContentMonthlyAdapter
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.ContentmonthlyfragmentBinding

class ContentMonthlyFragment : BaseFragment<ContentmonthlyfragmentBinding>(ContentmonthlyfragmentBinding::inflate) {
    private val mAdapter: ContentMonthlyAdapter by lazy { ContentMonthlyAdapter() }
    private val viewModel: EventViewModel by activityViewModels()
    val args: ContentMonthlyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView(args.contentArgs.position)
        setViewModel()

    }

    private fun setRecyclerView(position:Int) {
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
                ( it.year == args.contentArgs.year && it.month == args.contentArgs.months)
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