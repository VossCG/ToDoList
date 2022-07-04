package com.voss.todolist.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voss.todolist.presentation.ui.fragment.CalendarFragment


class CalendarViewPagerAdapter(fragment: Fragment) :FragmentStateAdapter(fragment){
    // index = 0 、13 ，is Fake page to buffer next page
    // 因為要實現 無限滑動的ViewPager，必須在前後加上一個fake page
    // 當到達fake pager的時候，馬上跳回到 first or last 頁面
    override fun getItemCount(): Int = 14

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(position)
    }

}


