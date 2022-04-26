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
import com.voss.todolist.UpdateRecyclerData
import com.voss.todolist.databinding.RowContenmonthlytitemBinding

class ContentListAdapter(private val updateRecyclerData: UpdateRecyclerData) :
    ListAdapter<EventTypes, ContentListAdapter.ContentViewHolder>(ListAdapterDiffUtil()) {


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
        holder.title.text = getItem(position).title
        holder.date.text = getItem(position).date.substring(5, 10)
        holder.content.text = getItem(position).content

        Log.d("Position",position.toString()+":"+getItem(position).date)

        holder.editButton.setOnClickListener {
            Log.d("Holder","list:${getItem(position).date}")
            updateRecyclerData.updateContentItem(getItem(position))
        }
    }

    inner class ContentViewHolder(binding: RowContenmonthlytitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowContentTitleTextView
        val content: TextView = binding.rowContentTextView
        val date: TextView = binding.rowContentDateTextView
        val showMoreContent: TextView = binding.rowShowMoreContentTextView
        val editButton: ImageButton = binding.rowContentEditBut
        val deleteButton: ImageButton = binding.rowContentDeleteBut


        init {
            deleteButton.setOnClickListener {
                updateRecyclerData.deleteContentItem(getItem(adapterPosition))
            }
        }
    }
}

class ListAdapterDiffUtil : DiffUtil.ItemCallback<EventTypes>() {
    override fun areItemsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EventTypes, newItem: EventTypes): Boolean {
        return oldItem == newItem
    }
}

