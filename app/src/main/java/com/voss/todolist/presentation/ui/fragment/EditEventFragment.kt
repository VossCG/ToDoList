package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.voss.todolist.data.EventTypes
import com.voss.todolist.databinding.FragmentEditeventBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<FragmentEditeventBinding>(FragmentEditeventBinding::inflate) {

    private val viewModel: EditEventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewOnClickEvent()
        setDatePickerListener()
    }

    private fun itemViewOnClickEvent() {
        binding.backHomeArrowBut.backArrowBut.setOnClickListener {
            navController.popBackStack()
        }

        binding.setUpEventBut.setOnClickListener {
            insertDataToDataBase()
        }
    }

    private fun insertDataToDataBase() {
        // get text From EditText
        val title = binding.titleEdittext.text.toString()
        val content = binding.contentEditText.text.toString()

        val dateInteger = viewModel.getDateInteger()
        val date = viewModel.getCurrentDate()

        if (checkData(title, date, content)) {
            val event = EventTypes(title, content, date, dateInteger)
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

