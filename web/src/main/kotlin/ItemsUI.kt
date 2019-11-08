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

fun Routing.runWeb() {
    route("api"){        
        get("allItems") {
            when {
                call.sessions.get<UserSession>() == null -> call.sessions.set(UserSession(name = "John"))
            }
            // getClientCredential("http://localhost:5000/connect/token", "todolistClient", "superSecretPassword", listOf("todolistAPI.read", "todolistAPI.write"))
            call.respond(
                call.respondText("on web")
            )
        }
    }
}