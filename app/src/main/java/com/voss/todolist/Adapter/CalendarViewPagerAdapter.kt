package com.voss.todolist.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voss.todolist.Fragment.CalendarFragment


class CalendarViewPagerAdapter(fragment: Fragment) :FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 12

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(position)
    }

}


