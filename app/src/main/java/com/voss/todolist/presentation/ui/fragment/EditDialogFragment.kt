package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.voss.todolist.databinding.DialogFragmentEditBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import java.util.*

class EditDialogFragment :
    BaseDialogFragment<DialogFragmentEditBinding>(DialogFragmentEditBinding::inflate) {
    private val viewModel: EditEventViewModel by activityViewModels()
    private val calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTb.setNavigationOnClickListener {
            showCancelAlertDialog()
        }
        binding.editEventCalendarTv.inputType = InputType.TYPE_NULL
        binding.editEventCalendarTv.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            binding.editEventCalendarTv.setText(viewModel.getFormatData(year, month, dayOfMonth))
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