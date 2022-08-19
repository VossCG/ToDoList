package com.voss.todolist.presentation.uiState

data class EditEventUiState(
    var eventType:String,
    var title:String,
    var content:String,
    var date:String,
    var dateInteger:Int
)