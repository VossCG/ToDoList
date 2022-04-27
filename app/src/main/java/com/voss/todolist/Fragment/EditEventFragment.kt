package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.EditeventfragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<EditeventfragmentBinding>(EditeventfragmentBinding::inflate) {

    private val evenViewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val TAG = EditEventFragment::class.java.simpleName

    private var mYear: Int = 0
    private var mMonth: Int = 0
    private var mDays: Int = 0


    private val calendar: Calendar by lazy { Calendar.getInstance() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewOnClickEvent()

        setViewModelObserve()

        setDatePickerListener()
    }

    private fun itemViewOnClickEvent() {
        binding.backHomeArrowBut.setOnClickListener {
            navController.popBackStack()
        }

        binding.setUpEventBut.setOnClickListener {
            insertDataToDataBase()
        }

    }

    private fun setViewModelObserve() {
        evenViewModel.date.observe(this) {
            binding.dateTextView.text = it
        }
    }

    private fun insertDataToDataBase() {
        // get text From EditText
        val title = binding.titleEdittext.text.toString()
        val content = binding.contentEditText.text.toString()

        // get Date from calendar / DatePicker view
        val dateInteger = evenViewModel.getDateInteger(mYear,mMonth,mDays)
        val date = evenViewModel.date.value!!

        // check EditText Data not empty
        if (checkData(title, date, content,mYear)) {

            val event = EventTypes(title, date, content, mYear, mMonth, mDays, dateInteger, 1)
            // insert Date To Room
            evenViewModel.addEvent(event)
            Toast.makeText(this.context, "Room in Successful", Toast.LENGTH_SHORT).show()

            // When insert the event to Room , navigate Fragment to HomeFragment check event show on or not
            navController.navigate(R.id.action_editEventFragment_to_homeFragment)
        }

        // When it's Empty Toast talk User fill out all fields
        else Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT).show()
    }

    private fun setDatePickerListener() {
        binding.datePicker.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            object : DatePicker.OnDateChangedListener {
                override fun onDateChanged(
                    view: DatePicker?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    mYear = year
                    mMonth = monthOfYear
                    mDays = dayOfMonth
                    evenViewModel.setDate(year, monthOfYear, dayOfMonth)
                }
            }
        )
    }

    private fun checkData(title: String, date: String, content: String, year: Int): Boolean {
        return (title.isNotEmpty() && date.isNotEmpty() && content.isNotEmpty() && year != 0)
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

