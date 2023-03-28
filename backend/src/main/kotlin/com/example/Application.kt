package com.example

import io.ktor.server.application.*
import com.example.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
  io.ktor.server.netty.EngineMain.main(args)

//  embeddedServer(Netty, port = 8000, host = "121.0.0.1", module = Application::module)
//    .start(wait = true)
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
  configureSerialization()
  configureDatabases()
  configureHTTP()
  configureSecurity()
  configureRouting()
}
