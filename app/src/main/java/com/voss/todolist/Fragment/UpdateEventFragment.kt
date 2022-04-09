package com.voss.todolist.Fragment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.UpdateeventfragmentBinding
import java.util.*
import kotlin.properties.Delegates

class UpdateEventFragment :
    BaseFragment<UpdateeventfragmentBinding>(UpdateeventfragmentBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    private var mYear by Delegates.notNull<Int>()
    private var mMonth by Delegates.notNull<Int>()
    private var mDay by Delegates.notNull<Int>()
    private var mDate: String = "yyyy/mm/dd"
    private var mDateInteger = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDateAttributesWithArgs()
        initView()

    }


    private fun initDateAttributesWithArgs() {
        mYear = args.eventTypes.year
        mMonth = args.eventTypes.month
        mDay = args.eventTypes.day
        mDateInteger = viewModel.getDateInteger(mYear, mMonth, mDay)
        mDate = viewModel.getDateFormat(mYear, mMonth, mDay)
    }

    private fun updateItemData() {
        val newTitle = binding.updateTitleEdittext.text.toString()
        val newContent = binding.updateContentEditText.text.toString()

        if (inputCheck(newTitle, newContent)) {
            val newEvent =
                EventTypes(newTitle, mDate, newContent, mYear, mMonth, mDay, mDateInteger, 0)
            newEvent.id = args.eventTypes.id
            viewModel.updateEvent(newEvent)
            Toast.makeText(this.context, "Change Successful!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        // init editText with args
        binding.updateContentEditText.setText(args.eventTypes.content)
        binding.updateTitleEdittext.setText(args.eventTypes.title)

        // init Date TextView
        viewModel.date.observe(this) {
            binding.updateDateTextView.text = it
        }
        viewModel.date.value = mDate

        // init but
        binding.updateUploadBut.setOnClickListener {
            updateItemData()
            navController.navigate(R.id.action_updateEventFragment_to_homeFragment)
        }
        binding.cancelUpdateBut.setOnClickListener {
            navController.popBackStack()
        }

        binding.dateUpdateBut.setOnClickListener {
            DatePickerDialog(
                this.context!!,
                datePickerListener,
                args.eventTypes.year,
                args.eventTypes.month,
                args.eventTypes.day
            ).show()
        }
    }

    val datePickerListener = object : DatePickerDialog.OnDateSetListener {
        override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

            // because DatePicker send back months was started from 0
            // So when we show it on View , must plus 1 let back normal format

            mYear = year
            mDay = dayOfMonth
            mMonth = month
            mDateInteger = viewModel.getDateInteger(year, month, dayOfMonth)
            mDate = viewModel.getDateFormat(year, month, dayOfMonth)

            viewModel.date.value = mDate
        }
    }

    private fun inputCheck(title: String, content: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(content))
    }



    override fun onStart() {
        super.onStart()
        activity?.findViewById<BottomNavigationView>(R.id.nav_bottom)?.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.nav_bottom)?.visibility = View.VISIBLE
    }
}

