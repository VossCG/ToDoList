package com.voss.todolist.Util

import androidx.recyclerview.widget.DiffUtil
import com.voss.todolist.Data.EventTypes

class MyDiffUtil(private val newList: List<EventTypes>, private val oldlist: List<EventTypes>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].id == oldlist[oldItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldlist[oldItemPosition].dateInteger == newList[newItemPosition].dateInteger
    }

}