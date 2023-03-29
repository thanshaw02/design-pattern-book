package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// these functions/routes are for testing purposes, I named this file the actual name I will need and will add the correct routes after testing
fun Route.customerRouting() {
  route("/design-patterns") {

    // get all customers
    get {
      if (inMemoryDesignPatterns.isNotEmpty()) {
        call.response.status(HttpStatusCode.OK)
        call.respond(inMemoryDesignPatterns)
      } else {
        call.respondText("{ \"message\": \"No design patterns found\" }\n", status = HttpStatusCode.OK)
      }
    }

    // get a customer by id
    get("{id?}") {
      val id = call.parameters["id"] ?: return@get run {
        call.response.status(HttpStatusCode.BadRequest)
        call.response.header("X-Missing-Id", "Missing ID")
        call.respondText("Missing required ID when fetching a design pattern\n")
      }

      val designPattern = inMemoryDesignPatterns.find { it.id == id } ?: return@get run {
        call.response.status(HttpStatusCode.NotFound)
        call.response.header("X-Pattern-Not-Found", id)
        call.respondText("Design pattern not found with id $id")
      }

      call.response.status(HttpStatusCode.OK)
      call.respond(designPattern)
    }

    // add a new customer
    post {
      try {
        val designPattern = call.receive<DesignPattern>()
//        val designPattern = call.receive<Any>()
        println(designPattern)
//        inMemoryDesignPatterns.add(designPattern)
//        call.response.status(HttpStatusCode.Created)
//        call.response.header("x-Success", "Nice!")
//        call.respond("Successfully added new Design Pattern")
        call.respondText("Successfully added new Design Pattern\n", status = HttpStatusCode.Created)
      } catch (e: ContentTransformationException) {
        call.response.status(HttpStatusCode.BadRequest)
        call.response.header("X-Bad-Body", if (e.cause != null) e.cause.toString() else "Body parsing error")
        call.respond(if (e.message != null) "${e.message}\n" else "POST body missing required attributes\\n")
//        call.respond(e.message ?: "POST body missing required attributes\n")
      }
    }

    // delete a customer by id
    delete("{id?}") {

    }
  }
}