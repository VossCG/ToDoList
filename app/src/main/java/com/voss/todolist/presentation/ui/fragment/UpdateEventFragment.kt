package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentUpdateEventBinding
import com.voss.todolist.presentation.viewModel.UpdateEventViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateEventFragment() : BaseFragment<FragmentUpdateEventBinding>(FragmentUpdateEventBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val argsEventTypes get() = args.event
    private val viewModel: UpdateEventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }

    private var newDateInteger: Int = 0
    private var newDate = MutableLiveData<String>()
    private var newEvent: Event? = null
    private var newType: String = ""

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
            newEvent = Event(newTitle, newContent, newDate.value!!, newDateInteger, newType)
            newEvent?.id = argsEventTypes.id
            viewModel.updateEvent(newEvent!!)
            Toast.makeText(this.context, "Change Successful!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        // init editText with args
        binding.updateContentEditText.setText(argsEventTypes.content)
        binding.updateTitleEdittext.setText(argsEventTypes.title)

        // init date with args
        newDate.postValue(argsEventTypes.date)
        newEvent = argsEventTypes
        newDateInteger = argsEventTypes.dateInteger
        newType = argsEventTypes.type

        // init chipGroup
        binding.updateChipGroup.selectedTypeChipGroup.forEach{ view->
            view as Chip
            view.isChecked = (view).text == argsEventTypes.type
        }
        binding.updateChipGroup.selectedTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            newType = group.findViewById<Chip>(checkedId).text.toString()
        }

        // init but
        binding.updateBut.setOnClickListener {
            updateItemData()
            navController.navigateUp()
        }

        binding.cancelUpdateBut.backArrowBut.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Message")
                .setMessage("是否要取消編輯?")
                .setPositiveButton("Yes") { _, _ ->
                    navController.navigateUp()
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

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            newDateInteger = viewModel.getDateInteger(year, month + 1, dayOfMonth)
            newDate.postValue(viewModel.getDateFormat(year, month, dayOfMonth))
        }

    private fun inputCheck(title: String, content: String): Boolean {
        return (title.isNotEmpty() && content.isNotEmpty())
    }
}
