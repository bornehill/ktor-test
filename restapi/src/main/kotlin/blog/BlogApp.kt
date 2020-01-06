package com.myktortest.blog

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.util.*
import io.ktor.content.TextContent
import io.ktor.jackson.jackson
import io.ktor.jackson.*
import com.fasterxml.jackson.core.util.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.*
import java.io.*
import com.myktortest.httpbin.*
import com.myktortest.blog.routingapi.*
import com.myktortest.repos.*
import com.myktortest.dataaccess.*
import org.koin.dsl.module.module
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext

val injectAppModule = module {
    single<ITaskService> { TaskService(get()) }
    single<ITaskListRepository> { TaskListRepositorySql() }
}

val headerContentV1 = ContentType("application", "vnd.todoapi.v1+json")

fun Application.main() {
    StandAloneContext.startKoin(listOf(injectAppModule))

    val taskService: ITaskService by inject()
    moduleWithDependencies(taskService)
}

fun Application.moduleWithDependencies(taskService: ITaskService) {
    install(Routing) {
        trace { application.log.trace(it.buildText()) }
        blogRouting(taskService)
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation){
        jackson(headerContentV1) {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(JavaTimeModule())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        }
    }
    // Here we handle unhandled exceptions from routes
    install(StatusPages) {
        exception<Throwable> { cause ->
            environment.log.error(cause)
            val error = HttpBinError(code = HttpStatusCode.InternalServerError, request = call.request.local.uri, message = cause.toString(), cause = cause)
            call.respond(error)
        }
        status(HttpStatusCode.NotFound) {
            call.respond(TextContent("Opps ${it.value} ${it.description}", ContentType.Text.Plain.withCharset(Charsets.UTF_8), it))
        }        
    }    
}