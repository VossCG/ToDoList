package com.voss.todolist.presentation.uiState

data class EditEventUiState(
    val eventType: String,
    val title: String,
    val content: String,
    val date: String,
    val dateInteger: Int
)