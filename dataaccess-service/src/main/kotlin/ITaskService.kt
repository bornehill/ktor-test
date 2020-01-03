package com.myktortest.dataaccess

import com.myktortest.shared.*

interface ITaskService {
    fun getAll(): List<Task>
    fun getTask(id: Int): Task
    fun delete(id: Int): Boolean
    fun create(task: Task): Boolean
    fun update(id: Int, task: Task): Boolean
}