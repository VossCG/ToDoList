package com.voss.todolist.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.Fragment.HomeFragmentDirections
import com.voss.todolist.Util.MyDiffUtil
import com.voss.todolist.databinding.RowListitemBinding

class HomeEventAdapter() : RecyclerView.Adapter<HomeEventAdapter.EventTodayViewHolder>() {

    private var oldList = emptyList<EventTypes>()
    var itemOnClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventTodayViewHolder {
        return EventTodayViewHolder(
            RowListitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventTodayViewHolder, position: Int) {
        holder.date.text = oldList[position].date.subSequence(5..9)
        holder.title.text = oldList[position].title
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    // inject newList Event , and let DiffUtil updated View with dispatchUpdate to Adapter
    fun setData(newList: List<EventTypes>) {
        val diffUtil = MyDiffUtil(newList, oldList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        // Use diffResult to updated adapter to refresh View
        diffResult.dispatchUpdatesTo(this)

        // update oldList , next time will compared to new ones
        oldList = newList
    }

    inner class EventTodayViewHolder(private val binding: RowListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.rowTitleTextView
        val date: TextView = binding.rowDateTextView

        init {
            title.setOnClickListener {
                itemOnClick.invoke(adapterPosition)
            }
        }
    }
}


