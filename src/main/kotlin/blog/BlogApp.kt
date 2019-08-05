package blog

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
import com.fasterxml.jackson.core.util.*
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.*
import java.io.*
import httpbin.*
import blog.routingapi.*

fun Application.main() {
    install(Routing) {
        blogRouting()
    }
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation){
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