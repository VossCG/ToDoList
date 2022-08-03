package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.DialogFragmentEditBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.displayToastShort
import java.util.*

class EditDialogFragment :
    BaseDialogFragment<DialogFragmentEditBinding>(DialogFragmentEditBinding::inflate) {
    private val viewModel: EditEventViewModel by activityViewModels()
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
            with(viewModel) {
                val newEvent = Event(
                    title.value!!,
                    content.value!!,
                    getCurrentDate(),
                    getDateInteger(),
                    type.value!!
                )
                viewModel.insertEvent(newEvent)
                displayToastShort(requireContext(), "successful save the event")
                dismiss()
            }
        } else
            displayToastShort(requireContext(), "Please filled all data")
    }

    private fun resetDialog() {
        binding.editEventCalendarTv.setText(dateDefaultVale)
        binding.editEventTitleEdt.setText("")
        binding.editEventContentEdt.setText("")
        binding.editEventTypeChipGroup.clearCheck()
    }

    private fun setInputChangeListener() {
        binding.editEventTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            // chip 沒有選取狀態下 id 為 -1
            if (checkedId != -1) {
                viewModel.setType(group.findViewById<Chip>(checkedId).text.toString())
            } else
                viewModel.setType("")
        }
        binding.editEventTitleEdt.addTextChangedListener {
            viewModel.setTitle(binding.editEventTitleEdt.text.toString())
        }
        binding.editEventContentEdt.addTextChangedListener {
            viewModel.setContent(binding.editEventContentEdt.text.toString())
        }
    }

    private fun checkInputData(): Boolean {
        with(viewModel) {
            return !(title.value.isNullOrEmpty() || content.value.isNullOrEmpty() || binding.editEventCalendarTv.text.toString() == dateDefaultVale || type.value.isNullOrEmpty())
        }
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            binding.editEventCalendarTv.setText(viewModel.getFormatData(year, month, dayOfMonth))
            viewModel.apply {
                setYear(year)
                setMonth(month + 1)
                setDay(dayOfMonth)
            }
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

    override fun dismiss() {
        resetDialog()
        super.dismiss()
    }
}