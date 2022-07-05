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
import com.voss.todolist.presentation.ui.adapter.CalendarDayEventListAdapter
import com.voss.todolist.presentation.ui.adapter.CalendarViewPagerAdapter
import com.voss.todolist.R
import com.voss.todolist.presentation.viewModel.CalendarViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*


class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var calendarAdapter: CalendarViewPagerAdapter
    private val dayEventAdapter: CalendarDayEventListAdapter by lazy { CalendarDayEventListAdapter() }
    private val viewModel: CalendarViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        setViewModelObserver()
        setCalendarViewPager()
        setDayEventRecyclerView()
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

    private fun backCurrentDate() {
        val calendar = Calendar.getInstance(Locale.TAIWAN)
        binding.calendarContainerViewpager.setCurrentItem(calendar.get(Calendar.MONTH) + 1, false)
        viewModel.setSelectItemDay(calendar.get(Calendar.DAY_OF_MONTH))
        viewModel.setMonth(calendar.get(Calendar.MONTH) + 1)
        viewModel.setYear(calendar.get(Calendar.YEAR))
    }

    private fun setViewModelObserver() {

        viewModel.currentYear.observe(viewLifecycleOwner) {
            binding.browseEventYearTv.text = "$it 年"
        }

        viewModel.currentMonth.observe(viewLifecycleOwner) {
            binding.browseEventMonthTv.text = it.toString() + "月"
        }

        viewModel.selectItemDay.observe(viewLifecycleOwner) {
            val selectedDayEvent = viewModel.getSingleDayEvent()
            // show the selected day  ### Use new fun to get SelectDate ###
            binding.browseEventSelectDayTv.text = viewModel.getCurrentDate()
            // if selectedDay event is empty ,show a view to remind user have any event
            if (selectedDayEvent.isEmpty()) {
                binding.browseEventHintTv.visibility = View.VISIBLE
            } else
                binding.browseEventHintTv.visibility = View.GONE
            // refresh current selected item eventList View
            dayEventAdapter.submitList(selectedDayEvent)
        }

        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            dayEventAdapter.submitList(viewModel.getSingleDayEvent())
        }
    }

    private fun setCalendarViewPager() {
        calendarAdapter = CalendarViewPagerAdapter(this)
        val viewpager = binding.calendarContainerViewpager
        viewpager.apply {
            adapter = calendarAdapter
            setPageTransformer(MarginPageTransformer(10))
            setCurrentItem(viewModel.currentMonth.value!!, false)

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

    private fun setDayEventRecyclerView() {
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
            adapter = dayEventAdapter
            dayEventAdapter.submitList(viewModel.getSingleDayEvent())
        }
        // set callback
        dayEventAdapter.apply {
            getExpandPosition = { clickPosition ->
                scrollToExpandPosition(clickPosition)
            }
            clickItemDelete = { event -> viewModel.deleteEvent(event) }
            clickITemUpdate = { event ->
                val direction =
                    BrowseEventFragmentDirections.actionBrowseEventFragmentToUpdateEventFragment(
                        event
                    )
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