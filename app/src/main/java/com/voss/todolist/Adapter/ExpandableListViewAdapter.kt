package com.voss.todolist.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavController
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Fragment.MonthFragmentDirections
import com.voss.todolist.R


class ExpandableListViewAdapter(
    val context: Context,
    val childMonthsList: List<List<EventTypes>>,
    val navController: NavController
) : BaseExpandableListAdapter() {
    val groupList =
        listOf<String>("一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月")

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
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.row_monthitem, parent, false)
        val title: TextView = view.findViewById(R.id.rowMonthTitle_TextView)
        title.text = groupList[groupPosition]
        val image: ImageView = view.findViewById(R.id.expandArrow_img)
        if (isExpanded) image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        else image.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)

        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.row_listitem, parent, false)
        val date = view.findViewById<TextView>(R.id.rowDate_textView)
        val title = view.findViewById<TextView>(R.id.rowTitle_textView)
        date.text = childMonthsList[groupPosition][childPosition].date.subSequence(5..9)
        title.text = childMonthsList[groupPosition][childPosition].title

        // 使用者操作上必須點選title才能跳轉，看能不能改成點選整個layout

        title.setOnClickListener {
            val direction = MonthFragmentDirections.actionMonthFragmentToContentFragment(
                ArgsToContent(
                    childPosition,
                    childMonthsList[groupPosition][childPosition].year,
                    childMonthsList[groupPosition][childPosition].month
                )
            )
            navController.navigate(direction)
        }

        return view
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

}