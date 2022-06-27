package com.ktor.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.response.*
import javax.naming.AuthenticationException

fun Application.configureStatusPages(){
    install(StatusPages){
        status(HttpStatusCode.NotFound) {
            call.respond(
                message = "Page not Found.",
                status = HttpStatusCode.NotFound
            )
        }
        exception<AuthenticationException> {
            call.respond(
                message = it.message ?: "Authentication Exception.",
                status = HttpStatusCode.Unauthorized
            )
        }
        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.message ?: "Internal Server Error")
        }
    }
}