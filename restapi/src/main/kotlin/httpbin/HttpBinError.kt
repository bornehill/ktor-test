package com.myktortest.httpbin

import io.ktor.http.*
import java.time.LocalDate

data class HttpBinError(
        val request: String,
        val message: String,
        val code: HttpStatusCode,
        val cause: Throwable? = null
)