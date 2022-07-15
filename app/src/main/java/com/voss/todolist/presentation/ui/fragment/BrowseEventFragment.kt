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
import com.voss.todolist.data.Event
import com.voss.todolist.data.args.EditArgs
import com.voss.todolist.data.args.EventCardArgs
import com.voss.todolist.presentation.viewModel.CalendarViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*


class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var calendarAdapter: CalendarViewPagerAdapter
    private val dayEventAdapter: CalendarDayEventListAdapter by lazy { CalendarDayEventListAdapter() }
    private val viewModel: CalendarViewModel by activityViewModels()
    private val navController: NavController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListener()

        setObserver()
        setCalendarViewPager()
        setDayEventRecyclerView()
    }

    private fun setClickListener() {

        // menu item click event
        binding.browseEventToolbar.setOnMenuItemClickListener {
            when (it.title) {
                "today" -> {
                    jumpToCurrentDay()
                    return@setOnMenuItemClickListener true
                }
                "add" -> {
                    val direction =
                        BrowseEventFragmentDirections.actionBrowseEventFragmentToEditEventFragment(
                            EditArgs(
                                viewModel.currentYear.value!!,
                                viewModel.currentMonth.value!!,
                                viewModel.selectItemDay.value!!
                            )
                        )
                    navController.navigate(direction)
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

    private fun jumpToCurrentDay() {
        val calendar = Calendar.getInstance(Locale.TAIWAN)
        binding.calendarContainerViewpager.setCurrentItem(calendar.get(Calendar.MONTH) + 1, false)
        viewModel.setSelectItemDay(calendar.get(Calendar.DAY_OF_MONTH))
        viewModel.setMonth(calendar.get(Calendar.MONTH) + 1)
        viewModel.setYear(calendar.get(Calendar.YEAR))
    }

    private fun setObserver() {

        viewModel.currentYear.observe(viewLifecycleOwner) {
            binding.browseEventYearTv.text = "$it 年"
        }

        viewModel.currentMonth.observe(viewLifecycleOwner) {
            binding.browseEventMonthTv.text = it.toString() + "月"
        }

        viewModel.selectItemDay.observe(viewLifecycleOwner) {
            binding.browseEventSelectDayTv.text = viewModel.getCurrentDate()
            refreshDayEventList(viewModel.getSingleDayEvent())
        }

        viewModel.readAllEvent.observe(viewLifecycleOwner) {
            refreshDayEventList(viewModel.getSingleDayEvent())
        }
    }

    private fun refreshDayEventList(eventList: List<Event>) {
        // if selectedDay event is empty ,show a view to remind user have any event
        if (eventList.isEmpty()) {
            binding.browseEventHintTv.visibility = View.VISIBLE
        } else
            binding.browseEventHintTv.visibility = View.GONE
        // refresh current selected item eventList View
        dayEventAdapter.submitList(eventList)
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
            navigateToContentCard = { itemPosition ->
                val direction =
                    BrowseEventFragmentDirections.actionBrowseEventFragmentToEventCardFragment(
                        EventCardArgs(
                            itemPosition,
                            viewModel.currentYear.value!!,
                            viewModel.currentMonth.value!!,
                            viewModel.selectItemDay.value!!
                        )
                    )
                navController.navigate(direction)
            }
        }
    }
}