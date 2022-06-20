package com.voss.todolist.Fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.voss.todolist.Adapter.CalendarViewPagerAdapter
import com.voss.todolist.ViewModel.BrowseEventViewModel
import com.voss.todolist.databinding.FragmentBrowseEventBinding
import java.util.*

class BrowseEventFragment :
    BaseFragment<FragmentBrowseEventBinding>(FragmentBrowseEventBinding::inflate) {
    private val mAdapter: CalendarViewPagerAdapter by lazy { CalendarViewPagerAdapter(this) }
    private val viewModel: BrowseEventViewModel by activityViewModels()
    private val calendar: Calendar by lazy { Calendar.getInstance(Locale.TAIWAN) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeCurrentDate()
        setCurrentDate()
        setCalendarViewPager()
    }
    private fun setCurrentDate() {
        // 注入當前的日期到ViewModel
        viewModel.apply {
            setYear(calendar.get(Calendar.YEAR))
            setMonth(calendar.get(Calendar.MONTH)+1)
            setDay(calendar.get(Calendar.DAY_OF_MONTH))
        }
    }

    // 觀察年、月、日，並刷新對應的UI，而月份會從0開始，所以呈現在UI上面有額外加上1
    private fun observeCurrentDate() {
        viewModel.currentYear.observe(viewLifecycleOwner) {
            Log.d("BrowseEvent", "currentYear $it")
            binding.browseEventYear.text = it.toString() + "年"
        }
        viewModel.currentMonth.observe(viewLifecycleOwner) {
            Log.d("BrowseEvent", "currentMonth $it")
            binding.browseEventMonth.text = it.toString() + "月"
        }
        viewModel.currentDay.observe(viewLifecycleOwner) {
            binding.browseEventCurrentIcon.calendarTodayTv.text = it.toString()
        }
    }

    // 開始設定CalendarView
    private fun setCalendarViewPager() {
        val viewpager = binding.calendarContainerViewpager
        viewpager.apply {
            setPageTransformer(MarginPageTransformer(10))
            adapter = mAdapter

            // 設定開始的頁面，由於要實現無線連播的ViewPager，會在前後多設定一個空頁面
            // 也就必須讓當前的月份多加上1，才能讓Page的position，與月份是對齊的
            // 0 與 13 會是保留空白的，一月就會從position 1 開始，以此類推
            setCurrentItem(calendar.get(Calendar.MONTH)+1, false)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)

                    // 當滑動靜止的時候，開始設定事件
                    if (state == ViewPager2.SCROLL_STATE_IDLE) {

                        when (viewpager.currentItem) {
                            // 當到達最後一頁時，跳轉到item = 1 (第二頁)
                            13 -> {
                                viewpager.setCurrentItem(1, false)
                                viewModel.apply {
                                    // 刷新當前年月的資料
                                    setMonth(1)
                                    plusYear(1)
                                }
                            }
                            // 當到達第一頁時，跳轉到item = 12 (倒數第二頁)
                            0 -> {
                                viewpager.setCurrentItem(12, false)
                                viewModel.apply {
                                    // 刷新當前年月的資料
                                    setMonth(12)
                                    plusYear(-1)
                                }
                            }
                            else -> {
                                Log.d("TAG", "currentItem:$currentItem")
                                viewModel.setMonth(currentItem)
                            }
                        }
                    }
                }
            })
        }
    }
}