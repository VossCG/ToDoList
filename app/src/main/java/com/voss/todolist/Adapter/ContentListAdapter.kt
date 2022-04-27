package com.voss.todolist.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.databinding.RowContenmonthlytitemBinding

class ContentListAdapter() :
    ListAdapter<EventTypes, ContentListAdapter.ContentViewHolder>(ListAdapterDiffUtil()) {

    var itemClickUpdate: (data: EventTypes) -> Unit = {}
    var itemClickDelete: (data: EventTypes) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            RowContenmonthlytitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ContentViewHolder(binding: RowContenmonthlytitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowContentTitleTextView
        val content: TextView = binding.rowContentTextView
        val date: TextView = binding.rowContentDateTextView
        val editButton: ImageButton = binding.rowContentEditBut
        val deleteButton: ImageButton = binding.rowContentDeleteBut


        init {
            deleteButton.setOnClickListener {
                itemClickDelete.invoke(getItem(adapterPosition))
            }
            editButton.setOnClickListener {
                itemClickUpdate.invoke(getItem(adapterPosition))
            }
        }

        fun onBind(data: EventTypes) {
            title.text = data.title
            date.text = data.date.substring(5, 10)
            content.text = data.content
        }
    }
}

class ListAdapterDiffUtil : DiffUtil.ItemCallback<EventTypes>() {
    override fun areItemsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem.dateInteger == newItem.dateInteger
    }
}
