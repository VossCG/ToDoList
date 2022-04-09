package com.voss.todolist.Data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "EventTypes")
data class EventTypes(

    val title: String,
    val date: String,
    val content: String,
    val year: Int,
    val month: Int,
    val day: Int,
    val dateInteger: Int,
    @ColumnInfo(name = "number")
    val number: Int?
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

