package com.example.backend

import com.example.backend.routes.DesignPatternRoutes
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.CorsHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch

/**
 * How to start the server:
 *  - "cd" into "/design-pattern-book/backend"
 *  - run "./gradlew run"
 *    - if an error is thrown run "./gradlew --stop" then re-run "./gradlew run"
 */

class MainVerticle : CoroutineVerticle() {

  override suspend fun start() {

    val router = Router.router(vertx)

    // Allow all origins (not secure but works for now)
    router.route().handler(CorsHandler.create("*"))

    // Create router endpoints
    router.apply {
      post("/design-patterns")
        .handler(BodyHandler.create())
        .coroutineHandler { context ->
          DesignPatternRoutes.addPattern(context)
        }

      put("/design-patterns/:id")
        .handler(BodyHandler.create())
        .coroutineHandler { context ->
          DesignPatternRoutes.editPattern(context)
        }

      get("/design-patterns")
        .handler(BodyHandler.create())
        .coroutineHandler { context ->
          DesignPatternRoutes.getAllPatterns(context)
        }

      get("/design-patterns/:id")
        .handler(BodyHandler.create())
        .coroutineHandler { context ->
          DesignPatternRoutes.getPatternById(context)
        }

      delete("/design-patterns/:id")
        .handler(BodyHandler.create())
        .coroutineHandler { context ->
          DesignPatternRoutes.removePattern(context)
        }
    }

    // Create the HTTP server
    vertx.createHttpServer()
      // Handle every request using the router
      .requestHandler(router)
      // Start listening
      .listen(8000)
      // Print the port
      .onSuccess { server ->
        println("\n\nDesign pattern HTTP server started on port ${server.actualPort()}\n\n")
      }
  }

  // NOTE: This was pulled from https://github.com/vert-x3/vertx-lang-kotlin/issues/194
  private fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) = handler {
    launch(it.vertx().dispatcher()) {
      try {
        fn(it)
      } catch (e: Exception) {
        it.fail(e)
      }
    }
  }
}
