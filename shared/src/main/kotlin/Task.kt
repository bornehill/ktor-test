package com.myktortest.shared

import java.time.LocalDate

data class Task(
        val id: Int,
        val title: String,
        val details: String,
        val assignedTo: String,
        val dueDate: LocalDate,
        val importance: Importance
)

enum class Importance {
    Low, Medium, High
}