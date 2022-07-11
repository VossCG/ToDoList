package com.voss.todolist.presentation.uiState

data class CalendarUiState(
    val title:String,
    val body:String,
    val year:Int,
    val month:Int,
    val day:Int,
    val callBack:()->Unit
)