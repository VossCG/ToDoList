package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.databinding.ItemviewBrowseYearTitleItemBinding

class BrowseRecyclerAdapter() :
    RecyclerView.Adapter<BrowseRecyclerAdapter.YearRecyclerViewHolder>() {
    private var list = emptyList<Int>()
    var navigateToMonthFragment: (Int) -> Unit = {}
    fun setDataList(data: List<Int>) {
        list = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearRecyclerViewHolder {
        return YearRecyclerViewHolder(
            ItemviewBrowseYearTitleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: YearRecyclerViewHolder, position: Int) {
        holder.year.text = list[position].toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class YearRecyclerViewHolder(binding: ItemviewBrowseYearTitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val year: TextView = binding.yearTitleTextView

        init {
            year.setOnClickListener {
                navigateToMonthFragment.invoke(list[absoluteAdapterPosition])
            }
        }
    }
}