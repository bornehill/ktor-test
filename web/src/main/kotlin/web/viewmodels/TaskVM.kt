package com.myktortest.web.viewmodels

import com.myktortest.shared.*

data class TaskVM(private val items: List<Task>, private val user: User) {
    val userName = user.name
    val taskItems = items
}