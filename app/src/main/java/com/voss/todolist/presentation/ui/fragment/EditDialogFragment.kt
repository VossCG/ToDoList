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
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.closeKeyboard
import com.voss.todolist.util.displayToastShort
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EditDialogFragment :
    BaseDialogFragment<DialogFragmentEditBinding>(DialogFragmentEditBinding::inflate) {
    private val viewModel: EditEventViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    private val dateDefaultVale: String = "設定活動時間"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        setInputChangeListener()
    }
    private fun setOnClickListener() {
        binding.editTb.setNavigationOnClickListener {
            showCancelAlertDialog()
        }
        // let editText can't edit
        binding.editEventCalendarTv.inputType = InputType.TYPE_NULL
        binding.editEventCalendarTv.setOnClickListener {
            closeKeyboard(it, requireActivity())
            showDatePickerDialog()
        }
        binding.editTb.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.menu_item_save -> {
                    insertInputData()
                    true
                }
                else -> false
            }
        }
    }

    private fun insertInputData() {
        if (checkInputData()) {
            with(viewModel.editUiState) {
                val newEvent = Event(title, content, date, dateInteger, eventType)
                viewModel.insertEvent(newEvent)
                displayToastShort(requireContext(), "successful save the event")
                dismiss()
            }
        } else
            displayToastShort(requireContext(), "Please filled all data")
    }

    private fun setInputChangeListener() {
        binding.editEventTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            // chip 沒有選取狀態下 id 為 -1
            if (checkedId != -1) {
                viewModel.editUiState.eventType =
                    group.findViewById<Chip>(checkedId).text.toString()
            } else
                viewModel.editUiState.eventType = ""
        }
        binding.editEventTitleEdt.addTextChangedListener {
            viewModel.editUiState.title = binding.editEventTitleEdt.text.toString()
        }
        binding.editEventContentEdt.addTextChangedListener {
            viewModel.editUiState.content = binding.editEventContentEdt.text.toString()
        }
    }

    private fun checkInputData(): Boolean {
        with(viewModel.editUiState) {
            return title.isNotEmpty() && content.isNotEmpty() && binding.editEventCalendarTv.text.toString() != dateDefaultVale && eventType.isNotEmpty()
        }
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            val pickDate = viewModel.getFormatData(year, month, dayOfMonth)
            binding.editEventCalendarTv.setText(pickDate)
            viewModel.editUiState.date = pickDate
            viewModel.editUiState.dateInteger = viewModel.getDateInteger(year, month, dayOfMonth)
        }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(requireContext(), datePickerListener, year, month, day).show()
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