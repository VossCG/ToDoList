package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.R
import com.voss.todolist.databinding.ItemviewSearchEventCardBinding
import com.voss.todolist.util.EventTypeDiffUtil

class SearChRecyclerAdapter() :
    ListAdapter<Event, SearChRecyclerAdapter.SearChViewHolder>(EventTypeDiffUtil()) {

    var itemExpand: (Int) -> Unit = {}
    var itemDelete: (Event) -> Unit = {}
    var itemUpdate: (Event) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearChViewHolder {
        return SearChViewHolder(
            ItemviewSearchEventCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearChViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class SearChViewHolder(private val binding: ItemviewSearchEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            val expandListener = View.OnClickListener { expandEvent(binding) }

            binding.searchItemExpandBtn.setOnClickListener(expandListener)

            binding.root.setOnClickListener(expandListener)

            binding.root.setOnLongClickListener {
                itemUpdate.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }

            binding.searchItemFinishImgBtn.setOnClickListener {
                itemDelete.invoke(getItem(adapterPosition))
            }
        }

        fun bind(position: Int) {
            binding.searchItemTitleTv.text = getItem(position).title
            binding.searchItemMonthTv.text = "${getItem(position).getMonth()}月"
            binding.searchItemDayTv.text = getItem(position).getDay().toString()
            binding.searchItemContentTv.text = getItem(position).content
        }

        private fun expandEvent(binding: ItemviewSearchEventCardBinding) {
            if (binding.searchItemExpandedContentLl.visibility == View.GONE) {
                binding.searchItemExpandedContentLl.visibility = View.VISIBLE
                binding.searchItemExpandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                itemExpand.invoke(adapterPosition)
            } else {
                binding.searchItemExpandedContentLl.visibility = View.GONE
                binding.searchItemExpandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
            }
        }

    }
}