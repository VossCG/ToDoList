package com.voss.todolist.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.voss.todolist.Adapter.CalendarEventListAdapter
import com.voss.todolist.Adapter.CalendarViewAdapter
import com.voss.todolist.R
import com.voss.todolist.ViewModel.BrowseEventViewModel
import com.voss.todolist.databinding.FragmentCalendarBinding
import java.util.*

class CalendarFragment() : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    val binding get() = _binding!!

    private var position: Int = 0
    private lateinit var mAdapter: CalendarViewAdapter
    private val viewModel: BrowseEventViewModel by activityViewModels()
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }

    constructor(position: Int) : this() {
        this.position = position
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // -1 是因為week 是從1開始，所以要丟給adapter，如果是星期日為1，但在GridView呈現是，是不需要位移的
        // 所以在 size offset 上面是要為 0
        val dayWeekOffset = getFirstDayWeekOfMonth() - 1
        Log.d("CalendarFragment", "firstWeek:$dayWeekOffset")

        mAdapter = CalendarViewAdapter(
            getCurrentMonthOfDays(position, viewModel.currentYear.value!!) + dayWeekOffset,
            dayWeekOffset
        )

        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            mAdapter.setData(viewModel.getMonthEvent(position))
        }
        setCalendarView()
    }

    private fun getFirstDayWeekOfMonth(): Int {
        viewModel.apply {
            calendar.set(Calendar.YEAR, this.currentYear.value!!)
            calendar.set(Calendar.MONTH, position - 1)
            calendar.set(Calendar.DAY_OF_MONTH, 1)
        }
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    private fun getCurrentMonthOfDays(month: Int, year: Int): Int {
        if (year % 4 == 0 && month == 2) {
            return 29
        }
        return when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> 28
            else -> 0
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setCalendarView() {
        mAdapter.getDrawableCallBack = {
            when (it) {
                "stroke" -> resources.getDrawable(R.drawable.stroke_1dp_yellow, null)
                "single" -> resources.getDrawable(R.drawable.shape_calendar_icon_single_event, null)
                "some" -> resources.getDrawable(R.drawable.shape_calendar_icon_some_event, null)
                "multitude" -> resources.getDrawable(R.drawable.shape_calendar_icon_multitude, null)
                "default" -> resources.getDrawable(R.drawable.shape_calendar_icon_default, null)
                else -> resources.getDrawable(R.drawable.shape_calendar_icon_default, null)
            }
        }
        mAdapter.getItemDay = { viewModel.selectItemDay.value = it }

        binding.calendarGridRecycler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 7, GridLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
        mAdapter.setData(viewModel.getMonthEvent(position))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}