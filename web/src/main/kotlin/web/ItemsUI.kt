package com.myktortest.web

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.auth.authenticate
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set
import java.time.LocalDate
import com.myktortest.web.viewmodels.*
import io.ktor.mustache.MustacheContent

val task = ItemVM(
    1,
    "Add database processing",
    "Add backend support to this code",
    "Kevin",
    LocalDate.of(2018, 12, 18),
    Importance.High
)

val task2 = ItemVM(
    2,
    "Add api development",
    "Add backend support to this code",
    "Other",
    LocalDate.of(2018, 12, 18),
    Importance.Medium
)

var tasks = listOf(task, task2)

fun Routing.runWeb() {
    route("api"){        
        get("allItems") {
            // when {
            //     call.sessions.get<UserSession>() == null -> call.sessions.set(UserSession(name = "John"))
            // }

            //todos = listOf(todo, todo)

            val taskVM = TaskVM(tasks, UserSession("Kevin Smith"))
            // getClientCredential("http://localhost:5000/connect/token", "todolistClient", "superSecretPassword", listOf("todolistAPI.read", "todolistAPI.write"))
            call.respond(
                MustacheContent("allTasks.hbs", mapOf("tasks" to taskVM))
            )
        }
    }
}