package com.voss.todolist.Data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "EventTypes")
data class EventTypes(
    val title: String,
    val content: String,
    val date: String,
    val dateInteger: Int,
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun getDay(): Int {
        return date.substring(8).toInt()
    }
    fun getMonth(): Int {
        return date.substring(5, 7).toInt()
    }
    fun getYear(): Int {
        return date.substring(0, 4).toInt()
    }
}

