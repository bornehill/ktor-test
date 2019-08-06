package httpbin

import io.ktor.http.*
import java.time.LocalDate

data class HttpBinError(
        val request: String,
        val message: String,
        val code: HttpStatusCode,
        val cause: Throwable? = null
)

data class Task(
        val id: Int,
        val title: String,
        val details: String,
        val assignedTo: String,
        val dueDate: LocalDate,
        val importance: Importance
)

enum class Importance {
    Low, Medium, High
}