package com.voss.todolist.Fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
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
    private var newDate: String = "yyyy/mm/dd"
    private var newDateInteger = 0
    private var newYear = 0
    private var newMonth = 0
    private var newDay = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

    }

    private fun updateItemData() {
        val newTitle = binding.updateTitleEdittext.text.toString()
        val newContent = binding.updateContentEditText.text.toString()

        if (inputCheck(newTitle, newContent)) {
            val newEvent = EventTypes(newTitle, newContent, newDate, newDateInteger)
            newEvent.id = argsEventTypes.id
            viewModel.updateEvent(newEvent)
            Toast.makeText(this.context, "Change Successful!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        // init editText with args
        binding.updateContentEditText.setText(argsEventTypes.content)
        binding.updateTitleEdittext.setText(argsEventTypes.title)

        // init ViewModel  to  update TextView
        viewModel.date.observe(viewLifecycleOwner) {
            binding.updateDateTextView.text = it
            newDate = it
        }
        viewModel.setDate(
            argsEventTypes.getYear(),
            argsEventTypes.getMonth(),
            argsEventTypes.getDay()
        )

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

        binding.dateUpdateBut.setOnClickListener {
            DatePickerDialog(
                this.requireContext(),
                datePickerListener,
                argsEventTypes.getYear(),
                argsEventTypes.getMonth(),
                argsEventTypes.getDay()
            ).show()
        }
    }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            newYear = year
            newMonth = month
            newDay = dayOfMonth
            newDateInteger = viewModel.getDateInteger(year, month, dayOfMonth)

            viewModel.setDate(year, month, dayOfMonth)
        }

    private fun inputCheck(title: String, content: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }
}

