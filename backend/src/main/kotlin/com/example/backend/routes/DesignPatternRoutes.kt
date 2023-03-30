package com.example.backend.routes

import com.example.backend.models.DesignPattern
import com.example.backend.providers.designPatternController
import com.google.gson.Gson
import io.vertx.core.Handler
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DesignPatternRoutes {
  suspend fun addPattern(context: RoutingContext) {
    val postBody = context.body().asJsonObject().toString()
//    val gson = Gson()
//    val designPattern = gson.fromJson(postBody, DesignPattern::class.java)
    println("POST body: $postBody")

//    designPatternController.addPattern(DesignPattern(
//      name = "",
//      forces = "",
//      resolution = "",
//      codeExamples = "",
//      precedingPatter = "",
//      followingPattern = ""
//    ))
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
