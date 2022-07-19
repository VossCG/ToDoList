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
import com.voss.todolist.databinding.FragmentEditeventBinding
import com.voss.todolist.presentation.viewModel.EditEventViewModel
import com.voss.todolist.util.disPlayToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EditEventFragment : BaseFragment<FragmentEditeventBinding>(FragmentEditeventBinding::inflate) {

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
        binding.datePicker.init(
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

    private fun switchAddBtnState() {
        val addBtn = binding.addEventBtn
        viewModel.apply {
            if (!title.value.isNullOrEmpty() && !content.value.isNullOrEmpty() && !type.value.isNullOrEmpty()) {
                addBtn.backgroundTintList =
                    resources.getColorStateList(R.color.lightYellow, null)
                addBtn.isClickable = true
            } else {
                addBtn.backgroundTintList =
                    resources.getColorStateList(R.color.darkGrey, null)
                addBtn.isClickable = false
            }
        }
    }

    private fun setObserver() {
        viewModel.title.observe(viewLifecycleOwner) { switchAddBtnState() }
        viewModel.content.observe(viewLifecycleOwner) { switchAddBtnState() }
        viewModel.type.observe(viewLifecycleOwner) { switchAddBtnState() }
    }

    private fun setOnClickListener() {
        binding.backHomeArrowBut.backArrowBut.setOnClickListener {
            navController.popBackStack()
        }
        binding.addEventBtn.setOnClickListener {
            insertEvent()
        }
    }

    private fun setInputChangeListener() {
        binding.editSelectedTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            // chip 沒有選取狀態下 id 為 -1
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

    private fun insertEvent() {
        // get all Edit Value
        val title = viewModel.title.value ?: ""
        val content = viewModel.content.value ?: ""
        val type = viewModel.type.value ?: ""
        val dateInteger = viewModel.getDateInteger()
        val date = viewModel.getCurrentDate()

        if (checkInputEvent(title, date, content, type)) {
            viewModel.addEvent(Event(title, content, date, dateInteger, type))
            disPlayToastShort(requireContext(), "新增成功")
            navController.navigateUp()
        } else if (type.isEmpty()) {
            disPlayToastShort(requireContext(), "活動類型尚未選擇")
        } else
            disPlayToastShort(requireContext(), "Please fill out all fields")
    }

    private fun checkInputEvent(title: String, date: String, content: String, type: String): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty() && type.isNotEmpty())
    }
}

