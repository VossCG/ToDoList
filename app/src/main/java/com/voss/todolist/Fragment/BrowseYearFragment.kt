package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.BrowseRecyclerAdapter
import com.voss.todolist.R
import com.voss.todolist.Util.LinearItemDecoration
import com.voss.todolist.Util.dpToPx
import com.voss.todolist.databinding.FragmentBrowseYearBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BrowseYearFragment : BaseFragment<FragmentBrowseYearBinding>(FragmentBrowseYearBinding::inflate) {
    private val mAdapter: BrowseRecyclerAdapter by lazy {  BrowseRecyclerAdapter()}
    private val navController : NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val yearList = resources.getIntArray(R.array.year)
        binding.yearRecyclerview.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@BrowseYearFragment.context)
            mAdapter.setDataList(yearList.toList())
            adapter = mAdapter
            addItemDecoration(LinearItemDecoration(dpToPx(requireContext(),10f)))
        }
        mAdapter.navigateToMonthFragment = {
            val direction = BrowseYearFragmentDirections.actionBrowseYearFragmentToBrowseMonthFragment(it)
            navController.navigate(direction)
        }

    }

}



