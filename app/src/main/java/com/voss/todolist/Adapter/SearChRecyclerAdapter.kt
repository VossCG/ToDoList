package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Util.MyDiffUtil
import com.voss.todolist.ViewModel.EventViewModel
import com.voss.todolist.databinding.RowSearchitemBinding

class SearChRecyclerAdapter(val viewModel: EventViewModel) : RecyclerView.Adapter<SearChRecyclerAdapter.SearChViewHolder>() {
    private var oldList = emptyList<EventTypes>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearChViewHolder {
        return SearChViewHolder(
            RowSearchitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearChViewHolder, position: Int) {
        holder.title.text = oldList[position].title
        holder.month.text = (oldList[position].month+1).toString()+"月"
        holder.day.text = oldList[position].day.toString()
        holder.content.text = oldList[position].content

    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    fun setData(newList: List<EventTypes>) {
        val diffUtil = MyDiffUtil(newList, oldList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        diffResult.dispatchUpdatesTo(this)
        oldList = newList
    }

    inner class SearChViewHolder(binding: RowSearchitemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowSearchTitleTextView
        val month: TextView = binding.rowSearchMonthTextView
        val day: TextView = binding.rowSearchDayTextView
        val expand = binding.rowSearchExpandLayout
        val content = binding.rowSearchContentTextView
        val complete = binding.rowSearchContentCompletebut
        val card = binding.rowSearchCardView

        init {

            card.setOnClickListener {
                if (expand.visibility == View.GONE)
                expand.visibility = View.VISIBLE
                else
                expand.visibility = View.GONE
            }
            complete.setOnClickListener {
                viewModel.deleteEvent(oldList[absoluteAdapterPosition])
            }

        }

    }
}