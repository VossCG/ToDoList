package com.voss.todolist.Adapter

import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.voss.todolist.Data.EventTypes
import com.voss.todolist.UpdateRecyclerData
import com.voss.todolist.Util.MyDiffUtil
import com.voss.todolist.databinding.RowContenmonthlytitemBinding
import kotlinx.parcelize.Parcelize

class ContentMonthlyAdapter(val updateRecyclerData: UpdateRecyclerData) : RecyclerView.Adapter<ContentMonthlyViewHolder>() {
    var oldList = emptyList<EventTypes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentMonthlyViewHolder {
        return ContentMonthlyViewHolder(
            RowContenmonthlytitemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ContentMonthlyViewHolder, position: Int) {
        val contentText = holder.content
        val mContent = oldList[position].content

        if (mContent.length >= 100) {
            val shortText = mContent.subSequence(0, 100)
            contentText.text = "$shortText..."
            holder.showMoreContent.visibility = View.VISIBLE

            holder.showMoreContent.setOnClickListener {
                contentText.text = mContent
                it.visibility = View.GONE
            }
        } else {
            holder.showMoreContent.visibility = View.GONE
            contentText.text = mContent
        }
        // check more show is needed or not
        holder.title.text = oldList[position].title
        holder.date.text = oldList[position].date.substring(5, 10)

        // set EditButton
        holder.editButton.setOnClickListener {
            updateRecyclerData.updateContentItem(oldList[position])
        }

    }


    fun setData(newList: List<EventTypes>) {
        val diffUtil = MyDiffUtil(newList, oldList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        // Use diffResult to updated adapter to refresh View
        diffResult.dispatchUpdatesTo(this)

        // update oldList , next time will compared to new ones
        oldList = newList
    }

    override fun getItemCount(): Int {
        return oldList.size
    }


}

class ContentMonthlyViewHolder(binding: RowContenmonthlytitemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val title: TextView = binding.rowContentTitleTextView
    val content: TextView = binding.rowContentTextView
    val date: TextView = binding.rowContentDateTextView
    val showMoreContent: TextView = binding.rowShowMoreContentTextView
    val editButton: ImageButton = binding.rowContentEditBut

}

@Parcelize
data class ArgsToContent(val position: Int, val year: Int, val months: Int) : Parcelable

