package com.voss.todolist.presentation.ui.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.forEach
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.FragmentUpdateEventBinding
import com.voss.todolist.presentation.viewModel.UpdateEventViewModel
import com.voss.todolist.util.disPlayToastShort
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UpdateEventFragment() : BaseFragment<FragmentUpdateEventBinding>(FragmentUpdateEventBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val argsEventTypes get() = args.event
    private val viewModel: UpdateEventViewModel by viewModels()
    private val navController: NavController by lazy { findNavController() }

    private var newDateInteger: Int = 0
    private var newDate = MutableLiveData<String>()
    private var newType: String = ""

    private val datePickerListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            newDateInteger = viewModel.getDateInteger(year, month + 1, dayOfMonth)
            newDate.postValue(viewModel.getDateFormat(year, month, dayOfMonth))
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObserver()
        setClickListener()
        setInputChangeListener()

        initView()
        initAttributes()
    }

    private fun initView() {
        binding.updateEventContentEdt.setText(argsEventTypes.content)
        binding.updateEventTitleEdt.setText(argsEventTypes.title)

        binding.updateEventTypeChipGroup.forEach { view ->
            view as Chip
            view.isChecked = (view).text == argsEventTypes.type
        }
    }

    private fun initAttributes() {
        // init date with args
        newDate.postValue(argsEventTypes.date)
        newDateInteger = argsEventTypes.dateInteger
        newType = argsEventTypes.type
    }

    private fun setObserver() {
        newDate.observe(viewLifecycleOwner) {
            binding.updateEventDateTv.text = it
        }
    }

    private fun setInputChangeListener() {

        binding.updateEventContentEdt.addTextChangedListener {
            switchUpdateBtnState(getInputTitle(), getInputContent(), newType)
        }
        binding.updateEventTitleEdt.addTextChangedListener {
            switchUpdateBtnState(getInputTitle(), getInputContent(), newType)
        }
        binding.updateEventTypeChipGroup.setOnCheckedChangeListener { group, checkedId ->
            newType = if (checkedId != -1) {
                group.findViewById<Chip>(checkedId).text.toString()
            } else ""
            switchUpdateBtnState(getInputTitle(), getInputContent(), newType)
        }

    }

    private fun setClickListener() {
        binding.updateEventInsertBtn.setOnClickListener {
            updateEvent()
            navController.navigateUp()
        }
        binding.updateEventCancelBtn.backArrowBtn.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Message")
                .setMessage("是否要取消編輯?")
                .setPositiveButton("Yes") { _, _ ->
                    navController.navigateUp()
                }
                .setNegativeButton("No", null)
                .show()
        }
        binding.updateEventChangeDateBtn.setOnClickListener {
            showDatePickerDialog()
        }
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

    private fun updateEvent() {
        val newEvent = Event(getInputTitle(), getInputContent(), newDate.value!!, newDateInteger, newType)
        newEvent.id = argsEventTypes.id
        viewModel.updateEvent(newEvent)
        disPlayToastShort(requireContext(), "Change Successful!!")
    }

    private fun checkInputData(title: String, content: String, type: String): Boolean {
        return (title.isNotEmpty() && content.isNotEmpty() && type.isNotEmpty())
    }

    private fun switchUpdateBtnState(title: String, content: String, type: String) {
        val finishBtn = binding.updateEventInsertBtn
        if (checkInputData(title, content, type)) {
            finishBtn.backgroundTintList = resources.getColorStateList(R.color.lightYellow, null)
            finishBtn.isClickable = true
        } else {
            finishBtn.backgroundTintList = resources.getColorStateList(R.color.darkGrey, null)
            finishBtn.isClickable = false
        }
    }

    private fun getInputContent(): String = binding.updateEventContentEdt.text.toString()

    private fun getInputTitle(): String = binding.updateEventTitleEdt.text.toString()

}

