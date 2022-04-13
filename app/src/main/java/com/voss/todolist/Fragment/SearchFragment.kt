package com.voss.todolist.Fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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

        binding.searChEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val inputTitle = binding.searChEditText.text.toString()
                if (inputTitle.isNotEmpty()) {
                    // 獲得關鍵字過濾資料
                    val filterData = viewModel.readAllEvent.value?.filter { it.title.contains(inputTitle) }
                    // 關閉鍵盤
                    closeKeyboard(view,requireActivity())
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

        binding.searchToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.contentSearch_item -> {
                    Toast.makeText(this.context, "Content", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                R.id.titleSearch_item -> {
                    Toast.makeText(this.context, "title", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                R.id.dateSearch_item -> {
                    Toast.makeText(this.context, "Date", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
            }
            return@setOnMenuItemClickListener false
        }

    }
}