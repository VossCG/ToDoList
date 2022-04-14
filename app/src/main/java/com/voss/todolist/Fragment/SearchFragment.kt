package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.voss.todolist.Adapter.SearChRecyclerAdapter
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.content
import com.voss.todolist.databinding.SearchfragmentBinding
import com.voss.todolist.title
import java.util.*

class SearchFragment : BaseFragment<SearchfragmentBinding>(SearchfragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private var filterFactor: String = title
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }

        binding.searChEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputData = binding.searChEditText.text.toString()
                if (inputData.isNotEmpty()) {
                    // 獲得關鍵字過濾資料
                    val filterData =
                        viewModel.readAllEvent.value?.filter {
                            when (filterFactor) {
                                title -> it.title.contains(inputData)
                                content -> it.content.contains(inputData)
                                else -> false
                            }
                        }
                    // 關閉鍵盤
                    closeKeyboard(view, requireActivity())
                    mAdapter.setData(filterData ?: emptyList())
                } else Toast.makeText(
                    this.context,
                    "Please enter title to SearCh",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.searchSheetLayout)

        // set Toolbar
        binding.searchToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.searchFilter_item -> {
                    if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    } else
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }

        //set BottomSheet  Button
        binding.changeTitleBut.setOnClickListener {
            binding.changeStatusTextView.text = "標題"
            filterFactor = title
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        }
        binding.changeContentBut.setOnClickListener {
            binding.changeStatusTextView.text = "內容"
            filterFactor = content
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        }

    }
}