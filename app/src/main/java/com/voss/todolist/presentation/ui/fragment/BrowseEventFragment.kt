package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.transition.MaterialSharedAxis
import com.voss.todolist.presentation.ui.adapter.CalendarEventListAdapter
import com.voss.todolist.presentation.ui.adapter.CalendarViewPagerAdapter
import com.voss.todolist.R
import com.voss.todolist.presentation.viewModel.BrowseEventViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*


class BrowseEventFragment : BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var calendarAdapter: CalendarViewPagerAdapter
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    private val eventListAdapter: CalendarEventListAdapter by lazy { CalendarEventListAdapter() }
    private val viewModel: BrowseEventViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    private val currentMonth = calendar.get(Calendar.MONTH)
    private val currentYear = calendar.get(Calendar.YEAR)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        observeCurrentDate()
        setCurrentDate()
        setCalendarViewPager()
        setEventRecyclerList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set Transition Animation
        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.Z,false)
    }

    private fun initView() {

        // menu item click event
        binding.browseEventToolbar.setOnMenuItemClickListener {
            when (it.title) {
                "today" -> {
                    backCurrentDate()
                    return@setOnMenuItemClickListener true
                }
                "add" -> {
                    navController.navigate(R.id.action_browseEventFragment_to_editEventFragment)
                    return@setOnMenuItemClickListener true
                }
                "search" -> {
                    navController.navigate(R.id.action_browseEventFragment_to_searchFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
    }

    // jump to the current date
    private fun backCurrentDate() {
        binding.calendarContainerViewpager.setCurrentItem(currentMonth + 1, false)
        viewModel.setSelectItemDay(currentDay)
        viewModel.setMonth(currentMonth + 1)
        viewModel.setYear(currentYear)
    }

    private fun observeCurrentDate() {
        // observe year item View
        viewModel.currentYear.observe(viewLifecycleOwner) {
            binding.browseEventYear.text = it.toString() + "年"
        }
        // observe month item View
        viewModel.currentMonth.observe(viewLifecycleOwner) {
            binding.browseEventMonth.text = it.toString() + "月"
        }
        // observe user select day item
        viewModel.selectItemDay.observe(viewLifecycleOwner) { selectDay ->
            // filter current selected day event
            val selectedDayEvent = viewModel.getMonthEvent(viewModel.currentMonth.value!!).filter {
                it.getDay() == selectDay
            }
            // show the selected day
            binding.browseEventSelectDayTv.text = viewModel.getDateFormat(
                viewModel.currentYear.value!!,
                viewModel.currentMonth.value!!-1,
                selectDay,
                calendar
            )
            // if selectedDay event is empty ,show a view to remind user have any event
            if (selectedDayEvent.isEmpty()) {
                binding.browseEventHintTv.visibility = View.VISIBLE
            } else
                binding.browseEventHintTv.visibility = View.GONE
            // refresh current selected item eventList View
            eventListAdapter.submitList(selectedDayEvent)
        }
        // observe repo room date，when data change ,get new dayEvent to refresh eventList
        viewModel.readAllEvent.observe(viewLifecycleOwner) { allEvent ->
            val dayEvent = allEvent.filter {
                it.getMonth() == viewModel.currentMonth.value && it.getDay() == viewModel.selectItemDay.value
            }
            eventListAdapter.submitList(dayEvent)
        }
    }

    private fun setCurrentDate() {
        // 注入當前的日期到ViewModel
        viewModel.apply {
            setYear(calendar.get(Calendar.YEAR))
            setMonth(calendar.get(Calendar.MONTH) + 1)
            setSelectItemDay(calendar.get(Calendar.DAY_OF_MONTH))
        }
    }

    private fun setCalendarViewPager() {
        calendarAdapter = CalendarViewPagerAdapter(this)
        val viewpager = binding.calendarContainerViewpager
        viewpager.apply {
            adapter = calendarAdapter
            setPageTransformer(MarginPageTransformer(10))
            setCurrentItem(currentMonth + 1, false)

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
            getExpandPosition = { clickPosition ->
                scrollToExpandPosition(clickPosition)
            }
            clickItemDelete = { event -> viewModel.deleteEvent(event) }
            clickITemUpdate = { event ->
                val direction = BrowseEventFragmentDirections.actionBrowseEventFragmentToUpdateEventFragment(event)
                navController.navigate(direction)
            }
        }
    }

    private fun scrollToExpandPosition(clickPosition: Int) {
        val layoutManager = binding.calendarDayEventListRecycler.layoutManager as LinearLayoutManager
        val lastPositionVisible = layoutManager.findLastVisibleItemPosition()
        if (clickPosition == lastPositionVisible) {
            layoutManager.scrollToPositionWithOffset(clickPosition, 0)
        }
    }
}