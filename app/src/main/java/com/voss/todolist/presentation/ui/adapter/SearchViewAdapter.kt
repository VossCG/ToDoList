package com.voss.todolist.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.data.Event
import com.voss.todolist.databinding.ItemviewSearchCardBinding
import com.voss.todolist.util.EventTypeDiffUtil


class SearchViewAdapter : ListAdapter<Event, SearchViewAdapter.SearchViewHolder>(EventTypeDiffUtil()) {

    var itemExpand: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            ItemviewSearchCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (position > 0)
            holder.checkDateEventIsMul(getItem(position).date, getItem(position - 1).date)
    }

    inner class SearchViewHolder(private val binding: ItemviewSearchCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val expandContent = binding.searchItemExpandContentTv

        init {
            binding.searchItemTitleCard.setOnClickListener {
                if (expandContent.visibility == View.GONE) {
                    expandContent.visibility = View.VISIBLE
                    itemExpand.invoke(adapterPosition)
                } else
                    expandContent.visibility = View.GONE
            }
        }

        fun bind(event: Event) {
            binding.searchItemDateTv.text = getDateFormatText(event.getYear(), event.getMonth(), event.getDay())
            binding.searchItemTitleTv.text = event.title
            binding.searchItemExpandContentTv.text = event.content
            binding.searchItemExpandContentTv.visibility = View.GONE
        }

        fun checkDateEventIsMul(curDate: String, preDate: String) {
            if (curDate == preDate)
                binding.searchItemDateTv.visibility = View.GONE
            else
                binding.searchItemDateTv.visibility = View.VISIBLE
        }

        private fun getDateFormatText(year: Int, month: Int, day: Int): String {
            return "$year 年 $month 月 $day 號"
        }
    }

}

