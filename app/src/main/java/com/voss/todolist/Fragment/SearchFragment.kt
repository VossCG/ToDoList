package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.voss.todolist.Adapter.SearChRecyclerAdapter
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.SearchfragmentBinding
import java.util.*

class SearchFragment : BaseFragment<SearchfragmentBinding>(SearchfragmentBinding::inflate) {
    private val viewModel: EventViewModel by activityViewModels()
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter() }
    private val bottomSheetFragment: BottomSheetFragment by lazy { BottomSheetFragment() }
    private var filterFactor: String = "標題"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.filterFactor.observe(this) {
            binding.changeStatusTextView.text = it
        }

        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }

        // button 呼叫 bottomSheetDialog
        binding.searchFilterBut.setOnClickListener {
            bottomSheetFragment.show(parentFragmentManager, "BottomSheetDialog")
        }

        binding.searChEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputData = binding.searChEditText.text.toString()
                if (inputData.isNotEmpty()) {
                    // 關閉鍵盤
                    closeKeyboard(view, requireActivity())
                    // 獲得關鍵字過濾資料，放入adapter
                    mAdapter.setData(viewModel.filterDataWithFactor(inputData))
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
}
