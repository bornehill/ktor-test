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
        val title: String,
        val details: String,
        val assignedTo: String,
        val LocalDate: LocalDate,
        val Importace: Int
)