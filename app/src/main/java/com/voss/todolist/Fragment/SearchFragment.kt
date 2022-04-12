package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Adapter.SearChRecyclerAdapter
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.SearchfragmentBinding
import java.util.*

class SearchFragment : BaseFragment<SearchfragmentBinding>(SearchfragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }

        // 更改為關鍵字搜尋功能，單純比對title的判斷 會讓搜尋只會找到一定相同的
        binding.searchGoBut.setOnClickListener {
            val inputTitle = binding.searChEditText.text.toString()
            if (inputTitle.isNotEmpty()) {
                val filterData = viewModel.readAllEvent.value?.filter {
                    it.title == inputTitle
                }
                mAdapter.setData(filterData!!)
            } else Toast.makeText(this.context, "Please enter title to SearCh", Toast.LENGTH_SHORT)
                .show()

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