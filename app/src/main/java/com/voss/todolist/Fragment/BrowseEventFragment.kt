package com.voss.todolist.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.voss.todolist.Adapter.CalendarEventListAdapter
import com.voss.todolist.Adapter.CalendarViewPagerAdapter
import com.voss.todolist.ViewModel.BrowseEventViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*


class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private val calendarAdapter: CalendarViewPagerAdapter by lazy { CalendarViewPagerAdapter(this) }
    private val eventListAdapter: CalendarEventListAdapter by lazy { CalendarEventListAdapter() }
    private val viewModel: BrowseEventViewModel by activityViewModels()
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.browseEventCurrentIcon.calendarTodayTv.text =
            calendar.get(Calendar.DAY_OF_MONTH).toString()

        observeCurrentDate()
        setEventRecyclerList()
        setCalendarViewPager()

        setCurrentDate()
    }

    private fun setCurrentDate() {
        // 注入當前的日期到ViewModel
        viewModel.apply {
            setYear(calendar.get(Calendar.YEAR))
            setMonth(calendar.get(Calendar.MONTH) + 1)
            setSelectItemDay(calendar.get(Calendar.DAY_OF_MONTH))
        }
    }

    private fun observeCurrentDate() {
        viewModel.currentYear.observe(viewLifecycleOwner) {
            binding.browseEventYear.text = it.toString() + "年"
        }
        viewModel.currentMonth.observe(viewLifecycleOwner) {
            binding.browseEventMonth.text = it.toString() + "月"
        }
        // 使用者點選當前的日期，將資料注入EventList 呈現
        viewModel.selectItemDay.observe(viewLifecycleOwner) { selectDay ->
            val dayEvent = viewModel.getMonthEvent(viewModel.currentMonth.value!!).filter {
                it.getDay() == selectDay
            }
            // set list event title
            binding.browseEventSelectDayTv.text =
                viewModel.getDateFormat(
                    viewModel.currentYear.value!!,
                    viewModel.currentMonth.value!!,
                    selectDay,
                    calendar
                )
            // remind user have any event
            if (dayEvent.isEmpty()) {
                binding.browseEventHintTv.visibility = View.VISIBLE
            } else
                binding.browseEventHintTv.visibility = View.GONE
            // refresh eventList View
            eventListAdapter.submitList(dayEvent)
        }
        // 當資料庫有變動的時候，EventList的itemView
        viewModel.readAllEvent.observe(viewLifecycleOwner) { allEvent ->
            val dayEvent = allEvent.filter {
                it.getMonth() == viewModel.currentMonth.value && it.getDay() == viewModel.selectItemDay.value
            }
            eventListAdapter.submitList(dayEvent)
        }
    }

    private fun setCalendarViewPager() {
        val viewpager = binding.calendarContainerViewpager
        viewpager.apply {
            setPageTransformer(MarginPageTransformer(10))
            adapter = calendarAdapter
            setCurrentItem(calendar.get(Calendar.MONTH) + 1, false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        13 -> {
                            viewpager.setCurrentItem(1, false)
                            viewModel.apply {
                                // 刷新當前年月的資料
                                setMonth(1)
                                plusYear(1)
                            }
                        }
                        0 -> {
                            viewpager.setCurrentItem(12, false)
                            viewModel.apply {
                                // 刷新當前年月的資料
                                setMonth(12)
                                plusYear(-1)
                            }
                        }
                        else -> viewModel.setMonth(position)
                    }
                }
            })
        }
    }

    private fun setEventRecyclerList() {
        // init recyclerView
        binding.calendarDayEventListRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = eventListAdapter
            eventListAdapter.submitList(
                viewModel.getMonthEvent(calendar.get(Calendar.MONTH))
                    .filter { it.getDay() == calendar.get(Calendar.DAY_OF_MONTH) })
        }
        // set callback
        eventListAdapter.apply {
            getClickPosition = { clickPosition ->
                val layoutManager =
                    binding.calendarDayEventListRecycler.layoutManager as LinearLayoutManager
                val lastPositionVisible = layoutManager.findLastVisibleItemPosition()
                if (clickPosition == lastPositionVisible) {
                    layoutManager.scrollToPositionWithOffset(clickPosition, 0)
                }
            }
            itemDelete = { event ->
                viewModel.deleteEvent(event)
            }
        }
    }

}