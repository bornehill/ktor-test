package com.myktortest.dataaccess

import com.myktortest.shared.*

interface ITaskService {
    fun getAll(): List<Task>
    fun getTodo(id: Int): Task
    fun delete(id: Int): Boolean
    fun create(todo: Task): Boolean
    fun update(id: Int, todo: Task): Boolean
}