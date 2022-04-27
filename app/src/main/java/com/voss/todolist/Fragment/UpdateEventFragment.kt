package com.voss.todolist.Fragment

import android.app.AlertDialog
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
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.*
import kotlin.properties.Delegates


@AndroidEntryPoint
class UpdateEventFragment :
    BaseFragment<UpdateeventfragmentBinding>(UpdateeventfragmentBinding::inflate) {

    private val args: UpdateEventFragmentArgs by navArgs()
    private val viewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
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
            navController.navigateUp()
        }

        binding.cancelUpdateBut.setOnClickListener {

            AlertDialog.Builder(this.context)
                .setTitle("Message")
                .setMessage("是否要取消編輯?")
                .setPositiveButton("Yes") { _, _ ->
                    navController.popBackStack()
                }
                .setNegativeButton("No", null)
                .show()
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

    private val datePickerListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> // DatePicker send back months was started from 0
            mYear = year
            mDay = dayOfMonth
            mMonth = month
            mDateInteger = viewModel.getDateInteger(year, month, dayOfMonth)
            mDate = viewModel.getDateFormat(year, month, dayOfMonth)

            viewModel.setDate(mYear,mMonth,mDay)
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

