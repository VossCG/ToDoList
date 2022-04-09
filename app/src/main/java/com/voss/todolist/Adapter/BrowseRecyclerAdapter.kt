package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Fragment.BrowseFragmentDirections
import com.voss.todolist.R
import com.voss.todolist.databinding.RowYearitemBinding

class BrowseRecyclerAdapter(val list: List<Int>, val navController: NavController) :
    RecyclerView.Adapter<BrowseRecyclerAdapter.YearRecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearRecyclerViewHolder {
        return YearRecyclerViewHolder(
            RowYearitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: YearRecyclerViewHolder, position: Int) {
        holder.year.text = list[position].toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class YearRecyclerViewHolder(binding: RowYearitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val year: TextView = binding.yearTitleTextView

        init {
            year.setOnClickListener {
                val direction =
                    BrowseFragmentDirections.actionBrowseFragmentToMonthFragment(list[absoluteAdapterPosition])
                navController.navigate(direction)
            }
        }
    }
}