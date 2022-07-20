package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.R
import com.voss.todolist.databinding.ItemviewSearchEventCardBinding
import com.voss.todolist.util.EventTypeDiffUtil
import kotlin.math.exp

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
        holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        itemExpand.invoke(position)
    }

    private fun closeExpanded(holder: SearChViewHolder) {
        holder.expandContent.visibility = View.GONE
        holder.expandBtn.setImageResource(R.drawable.ic_baseline_keyboard_arrow_right_24)
    }

    inner class SearChViewHolder(private val binding: ItemviewSearchEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val expandBtn: ImageButton = binding.searchItemExpandBtn
        val expandContent: LinearLayoutCompat = binding.searchItemExpandedContentLl
        val header: LinearLayoutCompat = binding.searchItemHeaderLv

        init {
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
    }
}