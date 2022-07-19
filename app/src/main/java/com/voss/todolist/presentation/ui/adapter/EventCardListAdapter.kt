package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.ItemviewEventCardBinding
import com.voss.todolist.util.EventTypeDiffUtil

class EventCardListAdapter :
    ListAdapter<Event, EventCardListAdapter.ContentViewHolder>(EventTypeDiffUtil()) {

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
            binding.eventCardItemDeleteBtn.setOnClickListener {
                itemClickDelete.invoke(getItem(adapterPosition))
            }
            binding.eventCardItemEditBtn.setOnClickListener {
                itemClickUpdate.invoke(getItem(adapterPosition))
            }
        }

        fun bind(data: Event) {
            binding.eventCardItemTitleTv.text = data.title
            binding.eventCardItemTypeTv.text = data.type
            binding.eventCardItemContentTv.text = data.content
        }
    }
}



