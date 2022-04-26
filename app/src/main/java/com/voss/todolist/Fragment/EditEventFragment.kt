package com.voss.todolist.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
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
import timber.log.Timber


@AndroidEntryPoint
class EditEventFragment :
    BaseFragment<EditeventfragmentBinding>(EditeventfragmentBinding::inflate) {

    private val evenViewModel: EventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val TAG = EditEventFragment::class.java.simpleName
    private var dateDetail: CalendarViewDetail = CalendarViewDetail(0, 0, 0)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemViewOnClickEvent()

        setViewModelObserve()
    }

    private fun itemViewOnClickEvent() {
        binding.backHomeArrowBut.setOnClickListener {
            navController.popBackStack()
        }

        binding.setUpEventBut.setOnClickListener {
            insertDataToDataBase()
        }

        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            dateDetail = CalendarViewDetail(year, month, dayOfMonth)
            Timber.d("$dateDetail")
            evenViewModel.setDate(year, month, dayOfMonth)
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
        val eventDateDetail = dateDetail
        val content = binding.contentEditText.text.toString()

        // get Date from calendar view
        val dateInteger =
            evenViewModel.getDateInteger(dateDetail.year, dateDetail.month, dateDetail.day)
        val date = evenViewModel.getDateFormat(dateDetail.year, dateDetail.month, dateDetail.day)

        // check EditText Data not empty
        if (checkData(title, date, content, eventDateDetail.year)) {
            val event = EventTypes(
                title,
                date,
                content,
                eventDateDetail.year,
                eventDateDetail.month,
                eventDateDetail.day,
                dateInteger,
                1
            )
            // insert Date To Room
            evenViewModel.addEvent(event)
            Toast.makeText(this.context, "Room in Successful", Toast.LENGTH_SHORT).show()

            // When insert the event to Room , navigate Fragment to HomeFragment check event show on or not
            navController.navigate(R.id.action_editEventFragment_to_homeFragment)
        }

        // When it's Empty Toast talk User fill out all fields
        else Toast.makeText(this.context, "Please fill out all fields", Toast.LENGTH_SHORT).show()


    }

    private fun checkData(title: String, data: String, content: String, year: Int): Boolean {
        return (title.isNotEmpty() && data.isNotEmpty() && content.isNotEmpty() && year != 0)
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

data class CalendarViewDetail(val year: Int, val month: Int, val day: Int)