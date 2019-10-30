package com.myktortest.blog

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*
import com.myktortest.httpbin.*
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.datatype.jsr310.*
import com.fasterxml.jackson.databind.*

class ApplicationTest {
    val mapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .enable(SerializationFeature.INDENT_OUTPUT)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    @Test
    fun testRequests() = withTestApplication(Application::main) {
        with(handleRequest(HttpMethod.Get, "/api/allTasks")) {
            assertEquals(HttpStatusCode.OK, response.status())
            val count = mapper.readValue<List<Task>>(response.content!!)
            assertEquals(count.size, 2)
        }
    }
}