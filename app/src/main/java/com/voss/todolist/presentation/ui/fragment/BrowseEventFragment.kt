package com.voss.todolist.presentation.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.voss.todolist.presentation.ui.adapter.CalendarDayEventListAdapter
import com.voss.todolist.presentation.ui.adapter.CalendarViewPagerAdapter
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.data.args.EventCardArgs
import com.voss.todolist.presentation.viewModel.CalendarViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var calendarAdapter: CalendarViewPagerAdapter
    private val dayEventAdapter: CalendarDayEventListAdapter by lazy { CalendarDayEventListAdapter() }
    private val viewModel: CalendarViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }
    private var browseJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()
        setObserver()

        initView()
    }

    private fun initView() {
        setCalendarViewPager()
        setDayEventRecyclerView()
    }

    private fun setClickListener() {
        // menu item click event
        binding.browseEventMonthTv.setOnMenuItemClickListener {
            when (it.title) {
                "today" -> {
                    moveToCurrentDate()
                    return@setOnMenuItemClickListener true
                }
                "add" -> {
                    EditDialogFragment().show(childFragmentManager, "Edit")
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

    private fun setObserver() {
        browseJob = lifecycleScope.launchWhenStarted {
            launch {
                viewModel.yearState.collectLatest { year ->
                    binding.browseEventYearTv.text = "$year 年"
                }
            }
            launch {
                viewModel.monthState.collectLatest { month ->
                    binding.browseEventMonthTv.title = "$month 月"
                }
            }
            launch {
                viewModel.selectDayState.collectLatest { day ->
                    viewModel.setDate(viewModel.getCurrentDate())
                }
            }
            launch {
                viewModel.dateState.collectLatest { date ->
                    binding.browseEventSelectedDayTv.text = date
                    viewModel.setDayEvent(date)
                }
            }
            launch {
                viewModel.dayEventState.collectLatest { dayEvent ->
                    checkEventIsEmpty(dayEvent)
                    dayEventAdapter.submitList(dayEvent)
                }
            }
        }
    }

    private fun setCalendarViewPager() {
        calendarAdapter = CalendarViewPagerAdapter(this)
        val viewpager = binding.browseEventCalendarVp
        viewpager.apply {
            adapter = calendarAdapter
            setPageTransformer(MarginPageTransformer(10))
            setCurrentItem(viewModel.monthState.value, false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        13 -> {
                            viewpager.setCurrentItem(1, false)
                            viewModel.apply {
                                // 刷新當前年月的資料
                                setMonth(1)
                                changeYear(+1)
                            }
                        }
                        0 -> {
                            viewpager.setCurrentItem(12, false)
                            viewModel.apply {
                                // 刷新當前年月的資料
                                setMonth(12)
                                changeYear(-1)
                            }
                        }
                        else -> viewModel.setMonth(position)
                    }
                }
            })
        }
    }

    private fun setDayEventRecyclerView() {
        setDayEventAdapter(dayEventAdapter)
        binding.browseEventDayEventRcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = dayEventAdapter
        }
    }

    private fun setDayEventAdapter(adapter: CalendarDayEventListAdapter) {
        adapter.apply {
            navigateToContentCard = { itemPosition ->
                val direction =
                    BrowseEventFragmentDirections.actionBrowseEventFragmentToEventCardFragment(
                        EventCardArgs(
                            itemPosition,
                            viewModel.yearState.value,
                            viewModel.monthState.value,
                            viewModel.selectDayState.value
                        )
                    )
                navController.navigate(direction)
            }
        }
    }

    private fun moveToCurrentDate() {
        val calendar = Calendar.getInstance(Locale.TAIWAN)
        binding.browseEventCalendarVp.setCurrentItem(calendar.get(Calendar.MONTH) + 1, false)
        resetCurrentDate(calendar)
    }

    private fun resetCurrentDate(calendar: Calendar) {
        viewModel.setDay(calendar.get(Calendar.DAY_OF_MONTH))
        viewModel.setMonth(calendar.get(Calendar.MONTH) + 1)
        viewModel.setYear(calendar.get(Calendar.YEAR))
    }

    private fun checkEventIsEmpty(eventList: List<Event>) {
        if (eventList.isEmpty()) {
            binding.browseEventHintTv.visibility = View.VISIBLE
        } else
            binding.browseEventHintTv.visibility = View.GONE
    }


    override fun onStop() {
        browseJob = null
        super.onStop()
    }
}