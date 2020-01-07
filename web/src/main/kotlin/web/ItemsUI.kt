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
import com.myktortest.shared.*
import com.myktortest.web.viewmodels.*
import io.ktor.mustache.MustacheContent
import com.myktortest.apiaccess.*

fun Routing.runWeb(apiService: IAPIService) {
    route("api"){        
        get("allItems") {
            // when {
            //     call.sessions.get<UserSession>() == null -> call.sessions.set(UserSession(name = "John"))
            // }

            //todos = listOf(todo, todo)

            val taskVM = TaskVM(apiService.getAll(), User("Kevin Smith"))
            // getClientCredential("http://localhost:5000/connect/token", "todolistClient", "superSecretPassword", listOf("todolistAPI.read", "todolistAPI.write"))
            call.respond(
                MustacheContent("allTasks.hbs", mapOf("tasks" to taskVM))
            )
        }
    }
}