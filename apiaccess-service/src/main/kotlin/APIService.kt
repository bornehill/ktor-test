package com.myktortest.apiaccess

import com.myktortest.shared.*
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get


class APIService : IAPIService {
    val client = HttpClient {
        install(JsonFeature) {
            serializer = JacksonSerializer()
            jackson {
                enable(SerializationFeature.INDENT_OUTPUT)
                registerModule(JavaTimeModule())
                disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            }
        }
    }
    val apiEndpoint = "http://localhost:5000/api/allTasks"

    override fun update(id: Int, task: Task): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun create(task: Task): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Int): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getAll(): List<Task> {
        return client.get(apiEndpoint)
    }

    override fun getTask(id: Int): Task {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}