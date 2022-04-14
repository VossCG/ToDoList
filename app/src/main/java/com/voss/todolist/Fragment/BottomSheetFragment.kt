package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.BottomsheetBinding

class BottomSheetFragment() : BottomSheetDialogFragment() {
    private val viewModel: EventViewModel by activityViewModels()
    private var _binding: BottomsheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_MaterialComponents_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomsheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.changeTitleBut.setOnClickListener {
            viewModel.filterFactor.value = "標題"
            dismiss()
        }
        binding.changeContentBut.setOnClickListener {
            viewModel.filterFactor.value = "內容"
            dismiss()
        }
    }

}