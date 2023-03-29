package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.example.plugins.*
import io.ktor.client.call.*

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.delete("/customer").apply {
            assertEquals(HttpStatusCode.BadRequest, status)
            assertEquals("Missing customer ID", bodyAsText())
        }
    }
}
