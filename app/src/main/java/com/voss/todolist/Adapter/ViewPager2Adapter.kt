package com.voss.todolist.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.voss.todolist.tabFragment.DayFragment
import com.voss.todolist.Fragment.MonthFragment
import com.voss.todolist.Fragment.BrowseFragment

class ViewPager2Adapter(manager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BrowseFragment()
            1 -> MonthFragment()
            2 -> DayFragment()
            else -> BrowseFragment()
        }
    }

}