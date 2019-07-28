package routingapi;

import io.ktor.routing.*
import io.ktor.response.*
import io.ktor.application.*
import io.ktor.http.*

fun Routing.blogRouting() {
    route("/api/blog"){
        get("/") {
            call.respondText("Respond from BlogRuting", ContentType.Text.Html)
        }
    }
}