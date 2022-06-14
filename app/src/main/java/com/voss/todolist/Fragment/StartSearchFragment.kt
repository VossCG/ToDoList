package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.MaterialContainerTransform
import com.voss.todolist.Adapter.StartSearchAdapter
import com.voss.todolist.R
import com.voss.todolist.databinding.FragmentStartSearchBinding

class StartSearchFragment :
    BaseFragment<FragmentStartSearchBinding>(FragmentStartSearchBinding::inflate) {
    private val mAdapter by lazy { StartSearchAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()


        binding.searchRecentlyRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            mAdapter.submitList(resources.getStringArray(R.array.searchRecord).toMutableList())
        }

        (requireView().parent as ViewGroup).viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            duration = 300
        }
        sharedElementReturnTransition = MaterialContainerTransform().apply {
            duration = 300
        }
    }
}