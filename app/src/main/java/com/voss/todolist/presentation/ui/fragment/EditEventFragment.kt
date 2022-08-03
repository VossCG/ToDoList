package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentEditEventBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.displayToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<FragmentEditEventBinding>(FragmentEditEventBinding::inflate) {

    private val viewModel: EditEventViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }
    private val editArgs: EditEventFragmentArgs by navArgs()
    private val dateTime get() = editArgs.dateTime

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setOnClickListener()
        setInputChangeListener()

        initViewModelData()
        initDatePicker()
    }

    private fun initViewModelData() {
        viewModel.setYear(dateTime.year)
        viewModel.setMonth(dateTime.month)
        viewModel.setDay(dateTime.day)
    }

    private fun initDatePicker() {
        binding.editEventDatePicker.init(
            dateTime.year,
            dateTime.month - 1,
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

    private fun setObserver() {
        viewModel.title.observe(viewLifecycleOwner) { switchInsertBtnState() }
        viewModel.content.observe(viewLifecycleOwner) { switchInsertBtnState() }
        viewModel.type.observe(viewLifecycleOwner) { switchInsertBtnState() }
    }

    private fun setOnClickListener() {
        binding.editEventCancelBtn.backArrowBtn.setOnClickListener {
            navController.popBackStack()
        }
        binding.editEventInsertBtn.setOnClickListener {
            insertEvent()
        }
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

    private fun switchInsertBtnState() {
        val insertBtn = binding.editEventInsertBtn
        // can click
        if (!viewModel.title.value.isNullOrEmpty() && !viewModel.content.value.isNullOrEmpty() && !viewModel.type.value.isNullOrEmpty()) {
            insertBtn.backgroundTintList = resources.getColorStateList(R.color.lightYellow, null)
            insertBtn.isClickable = true
        }
        // can't click
        else {
            insertBtn.backgroundTintList = resources.getColorStateList(R.color.darkGrey, null)
            insertBtn.isClickable = false
        }
    }

    private fun insertEvent() {
        // get all Edit Value
        val title = viewModel.title.value ?: ""
        val content = viewModel.content.value ?: ""
        val type = viewModel.type.value ?: ""
        val dateInteger = viewModel.getDateInteger()
        val date = viewModel.getCurrentDate()

        if (checkInputEvent(title, date, content, type)) {
            viewModel.insertEvent(Event(title, content, date, dateInteger, type))
            displayToastShort(requireContext(), "新增成功")
            navController.navigateUp()
        } else if (type.isEmpty()) {
            displayToastShort(requireContext(), "活動類型尚未選擇")
        } else
            displayToastShort(requireContext(), "Please fill out all fields")
    }

    private fun checkInputEvent(
        title: String,
        date: String,
        content: String,
        type: String
    ): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty() && type.isNotEmpty())
    }
}

