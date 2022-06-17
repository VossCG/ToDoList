package com.voss.todolist.Fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.voss.todolist.Adapter.CalendarViewPagerAdapter
import com.voss.todolist.ViewModel.BrowseEventViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*

class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private lateinit var mAdapter: CalendarViewPagerAdapter
    private val pages = MutableLiveData<Int>()
    private val viewModel: BrowseEventViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        observerChangeUi()
        setCalendarViewPager()
    }

    private fun initUi() {
        binding.browseEventYear.text = viewModel.currentYear.value.toString()
        binding.browseEventMonth.text = viewModel.currentMonth.value.toString()
        binding.browseEventCurrentIcon.calendarTodayTv.text = viewModel.currentDay.value.toString()
    }

    private fun setCalendarViewPager() {

        mAdapter = CalendarViewPagerAdapter(this)
        binding.calendarContainerViewpager.apply {
            setPageTransformer(MarginPageTransformer(10))
            adapter = mAdapter

            setOnScrollChangeListener(object :View.OnScrollChangeListener{
                override fun onScrollChange(
                    v: View?,
                    scrollX: Int,
                    scrollY: Int,
                    oldScrollX: Int,
                    oldScrollY: Int
                ) {
                    TODO("Not yet implemented")
                }
            })
            registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    pages.value = viewModel.currentMonth.value
                }
            })
        }
    }

    private fun observerChangeUi() {
        pages.observe(viewLifecycleOwner) {
            binding.browseEventMonth.text = it.toString() + "月"
        }
    }
}