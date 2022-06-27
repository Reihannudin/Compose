package com.ktor.plugins

import com.ktor.router.getAllHeroes
import com.ktor.router.root
import com.ktor.router.searchHeroes
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.request.*
import javax.naming.AuthenticationException

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        root()
        getAllHeroes()
        searchHeroes()

        get("/test2"){
            throw AuthenticationException()
        }
        get("/test3"){
            throw Throwable()
        }

        static("/boruto/heroes/images") {
            resources("images")
        }
    }
}
