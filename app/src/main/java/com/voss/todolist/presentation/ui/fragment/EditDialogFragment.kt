package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.DialogFragmentEditBinding
import com.voss.todolist.data.DateStore
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.closeKeyboard
import com.voss.todolist.util.displayToastShort
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditDialogFragment(private val focusDate: String) :
    BaseDialogFragment<DialogFragmentEditBinding>(DialogFragmentEditBinding::inflate) {
    private val viewModel: EditEventViewModel by viewModels()
    private var currentDate: DateStore

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val pickDate = viewModel.getFormatData(year, month, dayOfMonth)
            binding.editEventCalendarTv.setText(pickDate)
            viewModel.setStateDate(pickDate)
            viewModel.setStateDateInteger(year, month + 1, dayOfMonth)
        }

    init {
        currentDate = DateStore(
            year = focusDate.subSequence(0..3).toString().toInt(),
            month = focusDate.subSequence(5..6).toString().toInt(),
            day = focusDate.subSequence(8..9).toString().toInt()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initUiState()
        setOnClickListener()
        setInputChangeListener()
    }

    private fun initView() {
        binding.editEventCalendarTv.setText(focusDate)
    }

    private fun initUiState() {
        viewModel.setStateDate(focusDate)
        viewModel.setStateDateInteger(currentDate.year, currentDate.month, currentDate.day)
    }

    private fun setOnClickListener() {
        binding.editTb.setNavigationOnClickListener {
            showCancelAlertDialog()
        }
        // let editText can't edit
        binding.editEventCalendarTv.inputType = InputType.TYPE_NULL
        binding.editEventCalendarTv.setOnClickListener {
            closeKeyboard(it, requireActivity())
            showDatePickerDialog(currentDate.year, currentDate.month, currentDate.day)
        }
        binding.editTb.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_item_save -> {
                    if (checkInputData())
                        insertInputData()
                    else
                        displayToastShort(requireContext(), "Please filled all data")
                    true
                }
                else -> false
            }
        }
    }

    private fun insertInputData() {
        with(viewModel.editUiState) {
            viewModel.insertEvent(Event(title, content, date, dateInteger, eventType))
            displayToastShort(requireContext(), "successful save the event")
            dismiss()
        }
    }

    private fun setInputChangeListener() {
        binding.editEventTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            // chip 沒有選取狀態下 id 為 -1
            if (checkedId != -1) {
               viewModel.setStateType(group.findViewById<Chip>(checkedId).text.toString())
            } else
                viewModel.setStateType("")
        }
        binding.editEventTitleEdt.addTextChangedListener {
           viewModel.setStateTitle(binding.editEventTitleEdt.text.toString())
        }
        binding.editEventContentEdt.addTextChangedListener {
           viewModel.setStateContent(binding.editEventContentEdt.text.toString())
        }
    }

    private fun checkInputData(): Boolean {
        with(viewModel.editUiState) {
            return title.isNotEmpty() && content.isNotEmpty() && eventType.isNotEmpty()
        }
    }

    private fun showDatePickerDialog(year: Int, month: Int, day: Int) {
        DatePickerDialog(requireContext(), datePickerListener, year, month - 1, day).show()
    }

    private fun showCancelAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Message")
            .setMessage("是否要離開編輯")
            .setPositiveButton("Yes") { _, _ ->
                dismiss()
            }.setNegativeButton("no", null)
            .show()
    }
}