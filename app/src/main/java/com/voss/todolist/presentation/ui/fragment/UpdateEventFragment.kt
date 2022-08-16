package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
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
    private lateinit var edTitle: EditText
    private lateinit var edContent: EditText

    private val datePickerListener =
        DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            viewModel.setDateInteger(viewModel.getDateInteger(year, month + 1, dayOfMonth))
            viewModel.setDate((viewModel.getDateFormat(year, month, dayOfMonth)))
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initViewModel()

        setObserver()
        setClickListener()
        setInputChangeListener()
    }

    private fun initView() {
        edTitle = binding.updateEventTitleEdt
        edContent = binding.updateEventContentEdt

        edContent.setText(argsEventTypes.content)
        edTitle.setText(argsEventTypes.title)

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
        edTitle.addTextChangedListener {
            checkInputIsEmpty()
        }
        edContent.addTextChangedListener {
            checkInputIsEmpty()
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
        binding.updateEventToolbar.setNavigationOnClickListener {
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
        viewModel.type.observe(viewLifecycleOwner) { type ->
            checkInputIsEmpty()
        }
    }

    private fun updateEvent() {
        val newEvent =
            Event(
                edTitle.text.toString(),
                edContent.text.toString(),
                viewModel.date.value!!,
                viewModel.currentDateInteger,
                viewModel.type.value!!
            )
        newEvent.id = argsEventTypes.id
        viewModel.updateEvent(newEvent)
        displayToastShort(requireContext(), "Change Successful!!")
    }

    private fun checkInputIsEmpty() {
        if (!edTitle.text.isNullOrEmpty()
            && !edContent.text.isNullOrEmpty()
            && viewModel.type.value!!.isNotEmpty()
        ) {
            openInsertBtn()
        } else
            closeInsertBtn()
    }

    private fun closeInsertBtn() {
        binding.updateEventInsertBtn.backgroundTintList =
            resources.getColorStateList(R.color.darkGrey, null)
        binding.updateEventInsertBtn.isClickable = false
    }

    private fun openInsertBtn() {
        binding.updateEventInsertBtn.backgroundTintList =
            resources.getColorStateList(R.color.lightYellow, null)
        binding.updateEventInsertBtn.isClickable = true
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

