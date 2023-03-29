package com.example.plugins

import com.example.routes.*
import io.ktor.server.routing.*
import io.ktor.server.resources.Resources
import io.ktor.server.application.*

fun Application.configureRouting() {
  install(Resources)
  routing {
    customerRouting()
  }
}
