package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Util.StringDiffUtil
import com.voss.todolist.databinding.ItemviewRowTextviewBinding

class StartSearchAdapter :
    ListAdapter<String, StartSearchAdapter.StartSearchViewHolder>(StringDiffUtil()) {

    class StartSearchViewHolder(binding: ItemviewRowTextviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val searchRecordTextView: TextView = binding.searchRecordTv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartSearchViewHolder {
        return StartSearchViewHolder(
            ItemviewRowTextviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StartSearchViewHolder, position: Int) {
        holder.searchRecordTextView.text = getItem(position)
    }
}