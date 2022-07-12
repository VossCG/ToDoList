package com.voss.todolist.presentation.ui.adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.util.EventTypeDiffUtil
import com.voss.todolist.databinding.ItemviewDateEventContentBinding
import kotlinx.parcelize.Parcelize

class ContentListAdapter : ListAdapter<Event, ContentListAdapter.ContentViewHolder>(EventTypeDiffUtil()) {

    var itemClickUpdate: (data: Event) -> Unit = {}
    var itemClickDelete: (data: Event) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        return ContentViewHolder(
            ItemviewDateEventContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class ContentViewHolder(binding: ItemviewDateEventContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowContentTitleTextView
        val content: TextView = binding.rowContentTextView
        val date: TextView = binding.rowContentDateTextView
        private val deleteButton: ImageButton = binding.rowContentDeleteBut

        init {
            deleteButton.setOnClickListener {
                itemClickDelete.invoke(getItem(adapterPosition))
            }
            binding.root.setOnClickListener {
                itemClickUpdate.invoke(getItem(adapterPosition))
            }
        }

        fun onBind(data: Event) {
            title.text = data.title
            date.text = data.date.substring(5, 10)
            content.text = data.content
        }
    }
}

@Parcelize
data class ArgsToContent(val position: Int, val year: Int, val months: Int) : Parcelable

