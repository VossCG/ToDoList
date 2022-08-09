package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import androidx.core.view.forEach
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentUpdateEventBinding
import com.voss.todolist.presentation.viewModel.UpdateEventViewModel
import com.voss.todolist.util.displayToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateEventFragment() :
    BaseFragment<FragmentUpdateEventBinding>(FragmentUpdateEventBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val argsEventTypes get() = args.event
    private val viewModel: UpdateEventViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            viewModel.setDateInteger(viewModel.getDateInteger(year, month + 1, dayOfMonth))
            viewModel.setDate((viewModel.getDateFormat(year, month, dayOfMonth)))
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setClickListener()
        setInputChangeListener()

        initView()
        initViewModel()
    }

    private fun initView() {
        binding.updateEventContentEdt.setText(argsEventTypes.content)
        binding.updateEventTitleEdt.setText(argsEventTypes.title)
        binding.updateEventTypeChipGroup.forEach { view ->
            view as Chip
            view.isChecked = (view).text == argsEventTypes.type
        }
    }

    private fun initViewModel() {
        viewModel.setDate(argsEventTypes.date)
        viewModel.setTitle(argsEventTypes.title)
        viewModel.setContent(argsEventTypes.content)
        viewModel.setType(argsEventTypes.type)
        viewModel.setDateInteger(argsEventTypes.dateInteger)
    }

    private fun setInputChangeListener() {

        binding.updateEventContentEdt.addTextChangedListener {
            viewModel.setContent(binding.updateEventContentEdt.text.toString())
        }
        binding.updateEventTitleEdt.addTextChangedListener {
            viewModel.setTitle(binding.updateEventTitleEdt.text.toString())
        }
        binding.updateEventTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                viewModel.setType(group.findViewById<Chip>(checkedId).text.toString())
            } else
                viewModel.setType("")
        }
    }

    private fun setClickListener() {
        binding.updateEventInsertBtn.setOnClickListener {
            updateEvent()
            navController.navigateUp()
        }
        binding.updateEventToolbar.setNavigationOnClickListener{
            showCancelDialog()
        }
        binding.updateEventCalendarTv.inputType = InputType.TYPE_NULL
        binding.updateEventCalendarTv.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun showCancelDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Message")
            .setMessage("是否要取消編輯?")
            .setPositiveButton("Yes") { _, _ ->
                navController.navigateUp()
            }
            .setNegativeButton("No", null)
            .show()
    }

    private fun setObserver() {
        viewModel.date.observe(viewLifecycleOwner) { date ->
            binding.updateEventCalendarTv.setText(date)
        }
        viewModel.title.observe(viewLifecycleOwner) { title ->
            checkInputIsEmpty()
        }
        viewModel.content.observe(viewLifecycleOwner) { content ->
            checkInputIsEmpty()
        }
        viewModel.type.observe(viewLifecycleOwner) { type ->
            checkInputIsEmpty()
        }
    }

    private fun updateEvent() {
        val newEvent =
            Event(
                viewModel.title.value!!,
                viewModel.content.value!!,
                viewModel.date.value!!,
                viewModel.dateInteger.value!!,
                viewModel.type.value!!
            )
        newEvent.id = argsEventTypes.id
        viewModel.updateEvent(newEvent)
        displayToastShort(requireContext(), "Change Successful!!")
    }

    private fun checkInputIsEmpty() {
        with(viewModel) {
            if (title.value!!.isNotEmpty() && content.value!!.isNotEmpty() && type.value!!.isNotEmpty()) {
                openInsertBtn(binding.updateEventInsertBtn)
            } else
                closeInsertBtn(binding.updateEventInsertBtn)
        }
    }

    private fun closeInsertBtn(insertBtn: Button) {
        insertBtn.backgroundTintList = resources.getColorStateList(R.color.darkGrey, null)
        insertBtn.isClickable = false
    }

    private fun openInsertBtn(insertBtn: Button) {
        insertBtn.backgroundTintList = resources.getColorStateList(R.color.lightYellow, null)
        insertBtn.isClickable = true
    }

    private fun showDatePickerDialog() {
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

