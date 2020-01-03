package com.myktortest.web

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import java.io.*
import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.mustache.Mustache
import io.ktor.sessions.SessionStorageMemory
import io.ktor.sessions.Sessions
import io.ktor.sessions.cookie
import com.myktortest.repos.TaskListRepositorySql
import com.myktortest.dataaccess.TaskService

fun Application.main() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
        
    install(Routing) {
        val taskService = TaskService(TaskListRepositorySql())
        runWeb(taskService)
        staticResources()
    }
    // Here we handle unhandled exceptions from routes
    install(StatusPages) {
        exception<Throwable> { cause ->
                    call.respondText(cause.localizedMessage, ContentType.Text.Plain, HttpStatusCode.InternalServerError)
                    throw cause
        }
    }    
}

data class UserSession(val name: String)