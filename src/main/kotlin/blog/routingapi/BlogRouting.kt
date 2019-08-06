package blog.routingapi;

import io.ktor.routing.*
import io.ktor.response.*
import io.ktor.request.*
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
            get("{id}") {
                val id = call.parameters["id"]!!
                try{
                    val task = allTasks[id.toInt()]
                    call.respond(task)
                }catch(e: Throwable){
                    call.respond(HttpStatusCode.NotFound)
                }
            }
            post {
                val receiveTask = call.receive<Task>();

                val newTask = Task(allTasks.size +1, receiveTask.title, receiveTask.details, 
                    receiveTask.assignedTo, receiveTask.dueDate, receiveTask.importance)
                allTasks = allTasks + newTask
                call.respond(HttpStatusCode.Created, allTasks)
            }
        }
    }
}

val task1 = Task(
    1,
    "Add RestAPI data access",
     "Add database suport",
     "Me",
    LocalDate.of(2019, 12, 18),
    Importance.Medium
)

val task2 = Task(
    2,
    "Add RestAPI new endpoint",
     "Add search by id",
     "Me",
    LocalDate.of(2019, 12, 19),
    Importance.High
)

var allTasks = listOf(task1, task2)
