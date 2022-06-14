package com.voss.todolist.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.R
import com.voss.todolist.databinding.ItemviewBrowseMonthGroupItemBinding
import com.voss.todolist.databinding.ItemviewDateEventTitleBinding


class BrowseExpandableListAdapter(
    val context: Context,
    private val childMonthsList: List<List<EventTypes>>,
    private val groupList: Array<String>,
    private var childItemClickListener: (position: Int, year: Int, month: Int) -> Unit = { _, _, _ -> }
) : BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return groupList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return childMonthsList[groupPosition].size
    }

    override fun getGroup(groupPosition: Int): Any {
        return groupList[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return childMonthsList[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childMonthsList[groupPosition][childPosition].id.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding =
            ItemviewBrowseMonthGroupItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.apply {
            rowMonthTitleTextView.text = groupList[groupPosition]
            expandArrowImg.setImageResource(
                if (isExpanded) R.drawable.ic_baseline_keyboard_arrow_down_24
                else R.drawable.ic_baseline_keyboard_arrow_right_24
            )
        }
        return binding.root
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childData = childMonthsList[groupPosition][childPosition]
        val binding =
            ItemviewDateEventTitleBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.apply {
            rowDateTextView.text = childData.date.subSequence(5..9)
            rowTitleTextView.text = childData.title
            root.setOnClickListener {
                childItemClickListener.invoke(
                    childPosition,
                    childData.year,
                    childData.month
                )
            }
        }
        return binding.root
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}