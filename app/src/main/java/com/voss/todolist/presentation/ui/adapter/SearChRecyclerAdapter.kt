package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
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
        holder.bind(getItem(position))
        closeExpanded(holder)
        holder.header.setOnClickListener {
            if (holder.expandContent.visibility == View.GONE) {
                expandedContent(holder, position)
            } else
                closeExpanded(holder)
        }
    }

    private fun expandedContent(holder: SearChViewHolder, position: Int) {
        holder.expandContent.visibility = View.VISIBLE
        itemExpand.invoke(position)
    }

    private fun closeExpanded(holder: SearChViewHolder) {
        holder.expandContent.visibility = View.GONE
    }

    inner class SearChViewHolder(private val binding: ItemviewSearchEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val expandContent: LinearLayoutCompat = binding.searchItemExpandedContentLl
        val header = binding.searchTitleCardview

        init {
            header.setOnLongClickListener {
                itemUpdate.invoke(getItem(adapterPosition))
                return@setOnLongClickListener true
            }
        }

        fun bind(event: Event) {
            binding.searchItemTitleTv.text = event.title
            binding.searchItemMonthTv.text = "${event.getMonth()}月"
            binding.searchItemDayTv.text = event.getDay().toString()
            binding.searchItemContentTv.text = event.content
        }
    }
}