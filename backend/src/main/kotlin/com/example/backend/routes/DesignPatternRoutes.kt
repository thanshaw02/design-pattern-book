package com.example.backend.routes

import com.example.backend.errors.DesignPatternIOError
import com.example.backend.models.DesignPattern
import com.example.backend.providers.designPatternController
import com.google.gson.Gson
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object DesignPatternRoutes {
  private val gson = Gson()

  suspend fun addPattern(context: RoutingContext) {
    context.response().putHeader("Content-Type", "application/json")

    val postBody = context.body().asJsonObject() ?: run {
      // handle an empty request here
      serverLog("error reading POST request body")
      context.response().statusCode = 400
      context.response().putHeader("X-Pattern-Missing-Data", "true")
      context.json(json {
        obj(
          "error" to "POST body is missing required data"
        )
      })
      return
    }

    val newDesignPattern = gson.fromJson(postBody.toString(), DesignPattern::class.java)
    newDesignPattern.id = UUID.randomUUID().toString()
    designPatternController.addPattern(newDesignPattern)

    serverLog("successfully added new design pattern with id ${newDesignPattern.id}")
    context.response().statusCode = 201
    context.json(json {
      obj("id" to newDesignPattern.id)
    })
  }

  suspend fun editPattern(context: RoutingContext) {
    println("\n\nHello????\n\n")
    context.response().putHeader("Content-Type", "application/json")

    val id = context.pathParam("id") ?: run {
      // handle empty path param id
      serverLog("id not found in path")
      context.response().statusCode = 400
      context.response().putHeader("X-Path-Param-Missing", "id")
      context.json(json {
        obj("error" to "id missing in path")
      })
      return
    }

    val putBody = context.body().asJsonObject() ?: run {
      // handle an empty request here
      serverLog("error reading PUT request body")
      context.response().statusCode = 400
      context.response().putHeader("X-Pattern-Missing-Data", "true")
      context.json(json {
        obj(
          "error" to "PUT body is missing required data"
        )
      })
      return
    }

    try {
      val newDesignPattern = gson.fromJson(putBody.toString(), DesignPattern::class.java)
      newDesignPattern.id = id
      designPatternController.editPattern(id, newDesignPattern)

      serverLog("successfully edited design pattern with id $id")
      context.response().statusCode = 201
      context.json(json {
        obj("id" to id)
      })
    } catch (ex: DesignPatternIOError) {
      serverLog(ex.message ?: "failed to find design pattern with id $id")
      context.response().statusCode = 404
      context.response().putHeader("X-Pattern-Not-Found", id)
      context.json(json {
        obj("error" to "design pattern not found with id $id")
      })
    }
  }

  suspend fun getAllPatterns(context: RoutingContext) {
    val allDesignPatterns = designPatternController.getAllPatterns()
    context.response().statusCode = 200
    context.response().putHeader("Content-Type", "application/json")
    context.json(gson.toJson(allDesignPatterns))
  }

  suspend fun getPatternById(context: RoutingContext) {
    context.response().putHeader("Content-Type", "application/json")

    val id = context.pathParam("id") ?: run {
      // handle empty path param id
      serverLog("id not found in path")
      context.response().statusCode = 400
      context.response().putHeader("X-Path-Param-Missing", "id")
      context.json(json {
        obj("error" to "id missing in path")
      })
      return
    }

    try {
      val designPattern = designPatternController.getPatternById(id)

      serverLog("successfully fetched design pattern by id $id")
      context.response().statusCode = 200
      context.json(gson.toJson(designPattern))
    } catch (ex: DesignPatternIOError) {
      serverLog(ex.message ?: "failed to find design pattern with id $id")
      context.response().statusCode = 404
      context.response().putHeader("X-Pattern-Not-Found", id)
      context.json(json {
        obj("error" to "design pattern not found with id $id")
      })
    }
  }

  suspend fun removePattern(context: RoutingContext) {
    context.response().putHeader("Content-Type", "application/json")

    val id = context.pathParam("id") ?: run {
      // handle empty path param id
      serverLog("id not found in path")
      context.response().statusCode = 400
      context.response().putHeader("X-Path-Param-Missing", "id")
      context.json(json {
        obj("error" to "id missing in path")
      })
      return
    }

    try {
      designPatternController.removePattern(id)
      serverLog("successfully removed design pattern with id $id")
      context.response().statusCode = 201
      context.json(json {
        obj("id" to id)
      })
    } catch (ex: DesignPatternIOError) {
      serverLog(ex.message ?: "failed to remove design pattern with id $id")
      context.response().statusCode = 404
      context.response().putHeader("X-Pattern-Not-Found", id)
      context.json(json {
        obj("error" to "design pattern not found with id $id")
      })
    }
  }

  // NOTE: I'm using index 2 here because that is the previous context prior to this method being called
  private fun serverLog(message: String) {
    val serverLogDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS")
    val serverLogDate = LocalDateTime.now().format(serverLogDateFormat)
    val loggingFromMethod = Thread.currentThread().stackTrace[2].methodName
    println("LOG $serverLogDate [ ${loggingFromMethod}: $message ]")
  }
}
