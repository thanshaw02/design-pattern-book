package com.example.backend

import com.example.backend.routes.DesignPatternRoutes
import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CorsHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {

    val router = Router.router(vertx)

    // Allow all origins (not secure but works for now)
    router.route().handler(CorsHandler.create("*"))

    // Create router endpoints
    router.apply {
      post("/design-patterns/addPattern").handler(BodyHandler.create()).handler(DesignPatternRoutes::addPattern)
      put("/design-patterns/editPattern").handler(BodyHandler.create()).handler(DesignPatternRoutes::editPattern)
      get("/design-patterns").handler(BodyHandler.create()).handler(DesignPatternRoutes::getAllPatterns)
      get("/design-patterns/:id").handler(BodyHandler.create()).handler(DesignPatternRoutes::getPatternById)
      delete("/design-patterns/removePattern").handler(BodyHandler.create()).handler(DesignPatternRoutes::removePattern)
    }

    // Create the HTTP server
    vertx.createHttpServer()
      // Handle every request using the router
      .requestHandler(router)
      // Start listening
      .listen(8000)
      // Print the port
      .onSuccess { server ->
        println("HTTP server started on port " + server.actualPort())
      }
  }
}
