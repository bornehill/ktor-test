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
import com.myktortest.apiaccess.*
import org.koin.dsl.module.module
import org.koin.ktor.ext.inject
import org.koin.standalone.StandAloneContext

val injectAppModule = module {
    single<IAPIService> { APIService() }
}

fun Application.main() {
    StandAloneContext.startKoin(listOf(injectAppModule))

    val apiService: IAPIService by inject()
    moduleWithDependencies(apiService)
}

fun Application.moduleWithDependencies(apiService: IAPIService) {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }
        
    install(Routing) {
        runWeb(apiService)
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