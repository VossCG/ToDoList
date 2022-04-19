package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
    private val mAdapter: SearChRecyclerAdapter by lazy { SearChRecyclerAdapter(viewModel) }
    private var inputData: String = "null"

    private val rotateOpen :Animation by  lazy { AnimationUtils.loadAnimation(this.context,R.anim.rotate_open_anim) }
    private val rotateClose :Animation by  lazy { AnimationUtils.loadAnimation(this.context,R.anim.rotate_close_anim) }
    private val fromBottom :Animation by  lazy { AnimationUtils.loadAnimation(this.context,R.anim.from_button_anim) }
    private val toBottom :Animation by  lazy { AnimationUtils.loadAnimation(this.context,R.anim.to_bottom_anim) }

    private var isExpanded:Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.readAllEvent.observe(this) {
            mAdapter.setData(viewModel.filterDataWithFactor(inputData))
        }

        binding.filterFab.setOnClickListener {
            onAddButtonClicked()
            isExpanded = !isExpanded
        }
        binding.changeContentFab.setOnClickListener {
            viewModel.filterFactor.value = "content"
            Toast.makeText(this.context,"Content",Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            isExpanded = !isExpanded
        }
        binding.changeTitleFab.setOnClickListener {
            viewModel.filterFactor.value = "title"
            Toast.makeText(this.context,"title",Toast.LENGTH_SHORT).show()
            onAddButtonClicked()
            isExpanded = !isExpanded
        }

        binding.searchRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SearchFragment.context)
            adapter = mAdapter
        }

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
                    else mAdapter.setData(filterData)
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding.searChEditText.setText("")
    }

    private fun onAddButtonClicked(){
        setVisibility(isExpanded)
        setAnimation(isExpanded)
        setInVisibleUnClickable(isExpanded)
    }

    private fun setVisibility(isExpanded:Boolean){
        if(!isExpanded){
            binding.changeContentFab.visibility = View.VISIBLE
            binding.changeTitleFab.visibility = View.VISIBLE
        }else {
            binding.changeContentFab.visibility = View.GONE
            binding.changeTitleFab.visibility = View.GONE
        }
    }
    private fun setAnimation(isExpanded:Boolean){
        if(!isExpanded){
            binding.changeContentFab.startAnimation(fromBottom)
            binding.changeTitleFab.startAnimation(fromBottom)
            binding.filterFab.startAnimation(rotateOpen)
        }else{
            binding.changeContentFab.startAnimation(toBottom)
            binding.changeTitleFab.startAnimation(toBottom)
            binding.filterFab.startAnimation(rotateClose)
        }
    }
    private fun setInVisibleUnClickable(isExpanded: Boolean){
        if(!isExpanded){
            binding.changeTitleFab.isClickable = true
            binding.changeContentFab.isClickable = true
        }
        else{
            binding.changeTitleFab.isClickable = false
            binding.changeContentFab.isClickable = false
        }
    }

}
