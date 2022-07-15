package com.voss.todolist.data.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EventCardArgs(
    val position: Int,
    val year:Int,
    val month:Int,
    val day:Int
) : Parcelable