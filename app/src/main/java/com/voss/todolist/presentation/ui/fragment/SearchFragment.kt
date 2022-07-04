package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import com.voss.todolist.presentation.ui.adapter.SearChRecyclerAdapter
import com.voss.todolist.util.AnimUtil
import com.voss.todolist.util.setPreventQuickerClick
import com.voss.todolist.presentation.viewModel.EventViewModel
import com.voss.todolist.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private var inputData: String = "null"
    private val rotateOpen: Animation by lazy { AnimUtil.getRotateOpen(requireContext()) }
    private val rotateClose: Animation by lazy { AnimUtil.getRotateClose(requireContext()) }
    private val fromBottom: Animation by lazy { AnimUtil.getFromBottom(requireContext()) }
    private val toBottom: Animation by lazy { AnimUtil.getToBottom(requireContext()) }

    private var isExpanded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z,true)
        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            mAdapter.submitList(viewModel.filterDataWithFactor(inputData))
        }
        setFloatingActionBut()
        setRecyclerView()
        setSearchEditText(view)
    }

    private fun setSearchEditText(view: View) {
        binding.searChEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                inputData = binding.searChEditText.text.toString()
                if (inputData.isNotEmpty()) {
                    // 關閉鍵盤
                    closeKeyboard(view, requireActivity())
                    // 獲得關鍵字過濾資料，放入adapter
                    val filterData = viewModel.filterDataWithFactor(inputData)
                    if (filterData.isNullOrEmpty())
                        Toast.makeText(this.context, "搜尋條件 找不到相關資料 請重新查詢", Toast.LENGTH_SHORT)
                            .show()
                    else mAdapter.submitList(filterData)
                } else Toast.makeText(
                    this.context,
                    "Please enter title to SearCh",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun setFloatingActionBut() {
        binding.filterFab.setPreventQuickerClick {
            onAddButtonClicked()
            isExpanded = !isExpanded
        }

        binding.changeContentFab.setPreventQuickerClick {
            viewModel.setFilterFactor("content")
            Toast.makeText(this.context, "Content", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            isExpanded = !isExpanded
        }

        binding.changeTitleFab.setPreventQuickerClick {
            viewModel.setFilterFactor("title")
            Toast.makeText(this.context, "title", Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            isExpanded = !isExpanded
        }
    }

    private fun setRecyclerView() {
        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }
        mAdapter.apply {
            itemClick = { clickPosition ->
                val layoutManager = binding.searchRecycler.layoutManager as LinearLayoutManager
                val lastPositionVisible = layoutManager.findLastVisibleItemPosition()

                if (clickPosition == lastPositionVisible) {
                    layoutManager.scrollToPositionWithOffset(clickPosition, 0)
                }
            }
            itemDelete = { event ->
                viewModel.deleteEvent(event)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searChEditText.setText("")
    }

    private fun onAddButtonClicked() {
        setAnimation(isExpanded)
        setVisibility(isExpanded)
        setInVisibleUnClickable(isExpanded)
    }

    private fun setVisibility(isExpanded: Boolean) {
        if (!isExpanded) {
            binding.changeContentFab.visibility = View.VISIBLE
            binding.changeTitleFab.visibility = View.VISIBLE
            binding.changeContentTv.visibility = View.VISIBLE
            binding.changeTitleTv.visibility = View.VISIBLE
        } else {
            binding.changeContentFab.visibility = View.GONE
            binding.changeTitleFab.visibility = View.GONE
            binding.changeContentTv.visibility = View.GONE
            binding.changeTitleTv.visibility = View.GONE
        }
    }

    private fun setAnimation(isExpanded: Boolean) {
        if (!isExpanded) {
            binding.changeContentFab.startAnimation(fromBottom)
            binding.changeTitleFab.startAnimation(fromBottom)
            binding.filterFab.startAnimation(rotateOpen)
        } else {
            binding.changeContentFab.startAnimation(toBottom)
            binding.changeTitleFab.startAnimation(toBottom)
            binding.filterFab.startAnimation(rotateClose)
        }
    }

    private fun setInVisibleUnClickable(isExpanded: Boolean) {
        if (!isExpanded) {
            binding.changeTitleFab.isClickable = true
            binding.changeContentFab.isClickable = true
        } else {
            binding.changeTitleFab.isClickable = false
            binding.changeContentFab.isClickable = false
        }
    }

}
