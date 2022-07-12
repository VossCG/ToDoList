package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentEditeventBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<FragmentEditeventBinding>(FragmentEditeventBinding::inflate) {

    private val viewModel: EditEventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private var type: String = "工作"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewOnClickEvent()
        setDatePickerListener()
        setEditTextListener()
    }

    private fun itemViewOnClickEvent() {
        binding.backHomeArrowBut.backArrowBut.setOnClickListener {
            navController.popBackStack()
        }
        binding.setUpEventBut.setOnClickListener {
            insertDataToDataBase()
        }
        binding.editChipGroup.selectedTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            type = group.findViewById<Chip>(checkedId).text.toString()
        }
    }

    private fun setEditTextListener() {
        binding.contentEditText.addTextChangedListener { hintUpdateButtonColor() }
        binding.titleEdittext.addTextChangedListener { hintUpdateButtonColor() }
    }

    private fun hintUpdateButtonColor() {
        if (!binding.contentEditText.text.isNullOrEmpty() && !binding.titleEdittext.text.isNullOrEmpty()) {
            binding.setUpEventBut.backgroundTintList =
                resources.getColorStateList(R.color.lightYellow, null)
            binding.setUpEventBut.isClickable = true
        } else {
            binding.setUpEventBut.backgroundTintList =
                resources.getColorStateList(R.color.darkGrey, null)
            binding.setUpEventBut.isClickable = false
        }
    }

    private fun insertDataToDataBase() {
        // get text From EditText
        val title = binding.titleEdittext.text.toString()
        val content = binding.contentEditText.text.toString()

        val dateInteger = viewModel.getDateInteger()
        val date = viewModel.getCurrentDate()

        if (checkData(title, date, content)) {
            val event = Event(title, content, date, dateInteger, type)
            viewModel.addEvent(event)
            Toast.makeText(this.context, "新增成功", Toast.LENGTH_SHORT).show()
            navController.navigateUp()
        } else Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
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

    private fun checkData(title: String, date: String, content: String): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty())
    }
}

