package com.myktortest.dataaccess

import com.myktortest.shared.*
import com.myktortest.repos.*
import java.time.LocalDate

val task1 = Task(
    1,
    "Add database processing 1",
    "Add backend support to this code",
    "Kevin",
    LocalDate.of(2018, 12, 18),
    Importance.HIGH
)

val task2 = Task(
    2,
    "Add database processing 2",
    "Add backend support to this code",
    "Kevin",
    LocalDate.of(2018, 12, 18),
    Importance.HIGH
)

val tasks = listOf(task1, task2)

class TaskService(val taskListRepository: ITaskListRepository) : ITaskService {
    override fun update(id: Int, task: Task): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(task: Task): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): List<Task> {
        return tasks
    }

    override fun gettask(id: Int): Task {
        return tasks[id]
    }

}