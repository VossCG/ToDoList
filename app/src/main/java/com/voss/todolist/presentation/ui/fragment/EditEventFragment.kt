package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentEditeventBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.setToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<FragmentEditeventBinding>(FragmentEditeventBinding::inflate) {

    private val viewModel: EditEventViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private val editArgs: EditEventFragmentArgs by navArgs()
    private val dateTime get() = editArgs.dateTime
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModelData()
        setObserver()
        setOnClickEvent()
        setDatePickerListener()
        setInputChangeListener()
    }

    private fun initViewModelData() {
        Log.d("Edit","dateTime:$dateTime")
        viewModel.setYear(dateTime.year)
        viewModel.setMonth(dateTime.month)
        viewModel.setDay(dateTime.day)
    }

    private fun judgeEditButtonState() {
        val addButton = binding.addEventBut
        viewModel.apply {
            if (!title.value.isNullOrEmpty() && !content.value.isNullOrEmpty() && !type.value.isNullOrEmpty()) {
                addButton.backgroundTintList =
                    resources.getColorStateList(R.color.lightYellow, null)
                addButton.isClickable = true
            } else {
                addButton.backgroundTintList =
                    resources.getColorStateList(R.color.darkGrey, null)
                addButton.isClickable = false
            }
        }
    }

    private fun setObserver() {
        viewModel.title.observe(viewLifecycleOwner) { judgeEditButtonState() }
        viewModel.content.observe(viewLifecycleOwner) { judgeEditButtonState() }
        viewModel.type.observe(viewLifecycleOwner) { judgeEditButtonState() }
    }

    private fun setOnClickEvent() {
        binding.backHomeArrowBut.backArrowBut.setOnClickListener {
            navController.popBackStack()
        }
        binding.addEventBut.setOnClickListener {
            insertDataToDataBase()
        }
    }

    private fun setInputChangeListener() {
        binding.editSelectedTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                viewModel.setType(group.findViewById<Chip>(checkedId).text.toString())
            } else
                viewModel.setType("")
        }

        binding.titleEdittext.addTextChangedListener {
            viewModel.setTitle(binding.titleEdittext.text.toString())
        }
        binding.contentEditText.addTextChangedListener {
            viewModel.setContent(binding.contentEditText.text.toString())
        }
    }

    private fun insertDataToDataBase() {
        // get all Edit Value
        val title = viewModel.title.value ?: ""
        val content = viewModel.content.value ?: ""
        val type = viewModel.type.value ?: ""
        val dateInteger = viewModel.getDateInteger()
        val date = viewModel.getCurrentDate()

        if (checkData(title, date, content, type)) {
            viewModel.addEvent(Event(title, content, date, dateInteger, type))
            setToastShort(requireContext(), "新增成功")
            navController.navigateUp()
        } else if (type.isEmpty()) {
            setToastShort(requireContext(), "type not selected")
        } else
            setToastShort(requireContext(), "Please fill out all fields")
    }

    private fun setDatePickerListener() {
        binding.datePicker.init(
            dateTime.year,
            dateTime.month-1,
            dateTime.day
        ) { _, year, monthOfYear, dayOfMonth ->
            viewModel.apply {
                setYear(year)
                // dataPicker month start from 0 , so need to plus 1
                setMonth(monthOfYear + 1)
                setDay(dayOfMonth)
            }
        }
    }

    private fun checkData(title: String, date: String, content: String, type: String): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty() && type.isNotEmpty())
    }
}

