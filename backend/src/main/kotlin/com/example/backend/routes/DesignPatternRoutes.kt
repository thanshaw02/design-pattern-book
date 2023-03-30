package com.example.backend.routes

import io.vertx.core.Handler
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DesignPatternRoutes {
  fun addPattern(context: RoutingContext) {

  }

  fun editPattern(context: RoutingContext) {

  }

  fun getAllPatterns(context: RoutingContext) {

  }

  fun getPatternById(context: RoutingContext) {

  }

  fun removePattern(context: RoutingContext) {

  }

  // NOTE: I'm using index 2 here because that is the previous context prior to this method being called
  private fun serverLog(message: String) {
    val serverLogDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS")
    val serverLogDate = LocalDateTime.now().format(serverLogDateFormat)
    val loggingFromMethod = Thread.currentThread().stackTrace[2].methodName
    println("LOG $serverLogDate [ ${loggingFromMethod}: $message ]")
  }
}
