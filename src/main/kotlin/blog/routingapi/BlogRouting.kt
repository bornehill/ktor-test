package blog.routingapi;

import io.ktor.routing.*
import io.ktor.response.*
import io.ktor.application.*
import io.ktor.http.*
import httpbin.*
import java.time.LocalDate

fun Routing.blogRouting() {
    route("api"){
        route("blog"){
            get {
                call.respondText("Respond from BlogRuting", ContentType.Text.Html)
            }
        }
        route("allTasks"){
            get {
                call.respond(allTasks)
            }
        }        
    }
}

val task1 = Task(
    "Add RestAPI data access",
     "Add database suport",
     "Me",
    LocalDate.of(2019, 12, 18),
    1
)

val allTasks = listOf(task1)