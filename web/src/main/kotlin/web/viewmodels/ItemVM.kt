package com.myktortest.web.viewmodels

import java.time.LocalDate
import com.myktortest.web.UserSession

data class ItemVM(
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

data class TaskVM(private val items: List<ItemVM>, private val user: UserSession) {
    val userName = user.name
    val taskItems = items
}