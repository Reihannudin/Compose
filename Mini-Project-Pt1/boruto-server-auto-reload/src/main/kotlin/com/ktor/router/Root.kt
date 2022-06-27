package com.ktor.router

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.root(){
    get("/") {
        call.respond(message = "Hello, Army!", status = HttpStatusCode.OK)
    }
}