package com.voss.todolist.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Adapter.CalendarViewAdapter
import com.voss.todolist.R
import com.voss.todolist.databinding.FragmentCalendarBinding

class CalendarFragment() : Fragment() {
    private var _binding: FragmentCalendarBinding? = null
    val binding get() = _binding!!
    private var position: Int = 0
    private val mAdapter by lazy { CalendarViewAdapter() }

    constructor(position: Int) : this() {
        this.position = position
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dayOfMonth = mutableListOf<Int>()
        for (i in 1..31) {
            dayOfMonth.add(i)
        }
        setGridView(dayOfMonth)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setGridView(dayOfMonth: MutableList<Int>) {
        mAdapter.setData(dayOfMonth)
        mAdapter.getDrawableCallBack = {
            when (it) {
                "stroke" -> resources.getDrawable(R.drawable.stroke_1dp_white, null)
                "single" -> resources.getDrawable(R.drawable.shape_calendar_icon_single_event, null)
                "some" -> resources.getDrawable(R.drawable.shape_calendar_icon_some_event, null)
                "multitude" -> resources.getDrawable(R.drawable.shape_calendar_icon_multitude, null)
                "default"-> resources.getDrawable(R.color.black, null)
                else -> resources.getDrawable(R.color.black, null)
            }
        }
        binding.calendarGridRecycler.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 7, GridLayoutManager.VERTICAL, false)
            adapter = mAdapter
            stopScroll()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}