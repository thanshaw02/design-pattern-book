package com.example

import io.ktor.server.application.*
import com.example.plugins.*

// NOTE: If testing using cURL DO NOT use "127.0.0.1" or "localhost" for the host. You must use your machines IPv4 address instead,
//       you do not need to do this when testing in the browser, however.

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
  configureSerialization()
//  configureDatabases()
  configureHTTP()
//  configureSecurity()
  configureRouting()
}
