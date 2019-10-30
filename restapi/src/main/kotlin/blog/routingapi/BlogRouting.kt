package com.myktortest.blog.routingapi;

import io.ktor.routing.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.application.*
import io.ktor.http.*
import com.myktortest.httpbin.*
import java.time.LocalDate

fun Routing.blogRouting() {
    route("api"){
        route("blog"){
            get {
                call.respondText("Respond from BlogRuting", ContentType.Text.Html)
            }
        }

       accept(headerContentV1) {
           get("allTasksHeader") {
               call.respond(allTasks)
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

            put("{id}") {
                val id = call.parameters["id"]!!
                if(id == null){
                    call.respond(HttpStatusCode.BadRequest)
                    return@put
                }

                val foundItem = allTasks.getOrNull(id.toInt())
                if(foundItem == null){
                    call.respond(HttpStatusCode.NotFound)
                    return@put
                }

                val receiveTask = call.receive<Task>();
                allTasks = allTasks.filter{ it.id != receiveTask.id }
                allTasks = allTasks + receiveTask

                call.respond(HttpStatusCode.NoContent)
            }

            delete("{id}") {
                val id = call.parameters["id"]!!
                if(id == null){
                    call.respond(HttpStatusCode.BadRequest)
                    return@delete
                }

                val foundItem = allTasks.getOrNull(id.toInt())
                if(foundItem == null){
                    call.respond(HttpStatusCode.NotFound)
                    return@delete
                }

                allTasks = allTasks.filter{ it.id - 1 != id.toInt() }
                call.respond(HttpStatusCode.NoContent)
            }            
        }
    }
}

val task1 = Task(
    1,
    "Add to RestAPI data access",
     "Add database suport",
     "Me",
    LocalDate.of(2019, 12, 18),
    Importance.Medium
)

val task2 = Task(
    2,
    "Add to RestAPI new endpoint",
     "Add search by id",
     "Me",
    LocalDate.of(2019, 12, 19),
    Importance.High
)

var allTasks = listOf(task1, task2)

val headerContentV1 = ContentType("application", "vnd.todoapi.v1+json")
