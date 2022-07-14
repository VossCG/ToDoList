package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setOnClickEvent()
        setDatePickerListener()
        setInputChangeListener()
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
        viewModel.type.observe(viewLifecycleOwner) {judgeEditButtonState() }
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
            viewModel.year.value!!,
            viewModel.month.value!!,
            viewModel.day.value!!
        ) { _, year, monthOfYear, dayOfMonth ->
            viewModel.apply {
                setYear(year)
                setMonth(monthOfYear)
                setDay(dayOfMonth)
            }
        }
    }

    private fun checkData(title: String, date: String, content: String, type: String): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty() && type.isNotEmpty())
    }
}

