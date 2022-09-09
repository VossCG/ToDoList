package com.voss.todolist.presentation.uiState

data class UpdateUiState(
    val eventType: String,
    val title: String,
    val content: String,
    val date: String,
    val dateInteger: Int
)