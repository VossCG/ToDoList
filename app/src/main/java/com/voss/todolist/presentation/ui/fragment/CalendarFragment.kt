package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.voss.todolist.presentation.ui.adapter.CalendarViewAdapter
import com.voss.todolist.R
import com.voss.todolist.presentation.viewModel.CalendarViewModel
import com.voss.todolist.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class CalendarFragment() : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    val binding get() = _binding!!

    private var pagePosition: Int = 0
    private val viewModel: CalendarViewModel by activityViewModels()
    private val calendar = Calendar.getInstance()
    private var calendarJob: Job? = null
    private lateinit var mAdapter: CalendarViewAdapter

    constructor(position: Int) : this() {
        this.pagePosition = position
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        setObserver()
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCalendarRecyclerView()
    }

    private fun setObserver() {
        calendarJob = lifecycleScope.launch {
            viewModel.getMonthEvent(pagePosition).collectLatest { events ->
                mAdapter.setData(events)
            }
        }
    }

    private fun setCalendarRecyclerView() {
        setCalendarAdapter()
        binding.calendarGridRecycler.apply {
            setHasFixedSize(true)
            layoutManager =
                GridLayoutManager(requireContext(), 7, GridLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private fun setCalendarAdapter() {
        // -1 是因為week 是從 1 開始，要丟給adapter，如果是星期日為1，但在GridView呈現是，是不需要位移的
        // 所以在 size offset 上面是要為 0
        val dayWeekOffset = viewModel.getFirstWeekOfMonth(pagePosition, calendar) - 1
        val dayOfMonth = viewModel.getCurrentMonthOfDays(pagePosition, viewModel.yearState.value)
        mAdapter = CalendarViewAdapter(dayOfMonth, dayWeekOffset)

        mAdapter.setDrawableCallBack = {
            when (it) {
                "stroke" -> resources.getDrawable(R.drawable.stroke_1dp_yellow, null)
                "single" -> resources.getDrawable(R.drawable.shape_calendar_icon_single_event, null)
                "some" -> resources.getDrawable(R.drawable.shape_calendar_icon_some_event, null)
                "multitude" -> resources.getDrawable(R.drawable.shape_calendar_icon_multitude, null)
                "default" -> resources.getDrawable(R.drawable.shape_calendar_icon_default, null)
                else -> resources.getDrawable(R.drawable.shape_calendar_icon_default, null)
            }
        }
        mAdapter.getItemDayCallback = { day -> viewModel.setDay(day) }
    }

    override fun onStop() {
        calendarJob?.cancel()
        calendarJob = null
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}