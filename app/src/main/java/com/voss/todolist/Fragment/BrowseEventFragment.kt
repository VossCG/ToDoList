package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.voss.todolist.Adapter.CalendarViewPagerAdapter
import com.voss.todolist.databinding.FragmentBrowseEventBinding

class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var mAdapter: CalendarViewPagerAdapter
    private val pages = MutableLiveData<Int>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observerChangeUi()

        setCalendarViewPager()
    }

    private fun setCalendarViewPager() {
        val days = ArrayList<Int>()
        for (i in 1..31) {
            days.add(i)
        }

        val daysOfMonth = ArrayList<ArrayList<Int>>()
        for (i in 1..12) {
            daysOfMonth.add(days)
        }

        mAdapter = CalendarViewPagerAdapter(requireContext())
        binding.calendarContainerViewpager.adapter = mAdapter
        mAdapter.submit(daysOfMonth)
        binding.calendarContainerViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                pages.value = position + 1
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        })
    }

    private fun observerChangeUi() {
        pages.observe(viewLifecycleOwner) {
            binding.browseEventMonth.text = it.toString() + "月"
        }
    }
}