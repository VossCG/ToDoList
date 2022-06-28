package com.voss.todolist.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.FragmentUpdateEventBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateEventFragment :
    BaseFragment<FragmentUpdateEventBinding>(FragmentUpdateEventBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val argsEventTypes get() = args.eventTypes
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private var newDate = MutableLiveData<String>("yyyy/mm/dd")
    private var newDateInteger = argsEventTypes.dateInteger

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newDate.observe(viewLifecycleOwner) {
            binding.updateDateTextView.text = it
        }
        initView()

    }

    private fun updateItemData() {
        val newTitle = binding.updateTitleEdittext.text.toString()
        val newContent = binding.updateContentEditText.text.toString()
        if (inputCheck(newTitle, newContent)) {
            val newEvent = EventTypes(newTitle, newContent, newDate.value!!, newDateInteger)
            newEvent.id = argsEventTypes.id
            viewModel.updateEvent(newEvent)
            Toast.makeText(this.context, "Change Successful!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        // init editText with args
        binding.updateContentEditText.setText(argsEventTypes.content)
        binding.updateTitleEdittext.setText(argsEventTypes.title)

        // init date with args
        newDate.value = argsEventTypes.date

        // init but
        binding.updateUploadBut.setOnClickListener {
            updateItemData()
            navController.navigateUp()
        }
        binding.cancelUpdateBut.backArrowBut.setOnClickListener {
            AlertDialog.Builder(this.context)
                .setTitle("Message")
                .setMessage("是否要取消編輯?")
                .setPositiveButton("Yes") { _, _ ->
                    navController.popBackStack()
                }
                .setNegativeButton("No", null)
                .show()
        }

        binding.changeDateBut.setOnClickListener {
            // init DatePicker
            DatePickerDialog(
                requireContext(),
                datePickerListener,
                argsEventTypes.getYear(),
                // 丟給datePicker，因為month是從0開始，所以要 -1
                argsEventTypes.getMonth() - 1,
                argsEventTypes.getDay()
            ).show()
        }
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            newDateInteger = viewModel.getDateInteger(year, month+1, dayOfMonth)
            newDate.value = viewModel.getDateFormat(year, month, dayOfMonth)
            binding.updateDateTextView.text = viewModel.getDateFormat(year, month, dayOfMonth)
        }

    private fun inputCheck(title: String, content: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}

