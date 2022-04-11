package com.voss.todolist.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Util.MyDiffUtil
import com.voss.todolist.databinding.RowSearchitemBinding

class SearChRecyclerAdapter : RecyclerView.Adapter<SearChRecyclerAdapter.SearChViewHolder>() {
    private var oldList = emptyList<EventTypes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearChViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: SearChViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    fun setData(newList: List<EventTypes>) {
        val diffUtil = MyDiffUtil(newList, oldList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        diffResult.dispatchUpdatesTo(this)
        oldList = newList
    }

    class SearChViewHolder(binding: RowSearchitemBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}