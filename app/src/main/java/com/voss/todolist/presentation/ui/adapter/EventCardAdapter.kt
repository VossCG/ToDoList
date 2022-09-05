package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.R
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.ItemviewEventCardBinding
import com.voss.todolist.util.EventTypeDiffUtil

class EventCardAdapter :
    ListAdapter<Event, EventCardAdapter.ContentViewHolder>(EventTypeDiffUtil()) {

    var itemClickUpdate: (data: Event) -> Unit = {}
    var itemClickDelete: (data: Event) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemviewEventCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ContentViewHolder(private val binding: ItemviewEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.eventCardItemTb.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.more_delete_menuItem -> {
                        itemClickDelete.invoke(getItem(adapterPosition))
                        return@setOnMenuItemClickListener true
                    }
                    R.id.more_edit_menuItem -> {
                        itemClickUpdate.invoke(getItem(adapterPosition))
                        return@setOnMenuItemClickListener true
                    }
                    else -> return@setOnMenuItemClickListener false
                }
            }
        }

        fun bind(data: Event) {
            binding.eventCardItemTitleTv.text = data.title
            binding.eventCardItemTb.title = data.type
            binding.eventCardItemContentTv.text = data.content
        }
    }
}



