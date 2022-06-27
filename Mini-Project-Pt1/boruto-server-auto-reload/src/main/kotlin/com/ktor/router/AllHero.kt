package com.ktor.router

import com.ktor.models.ApiResponse
import com.ktor.repository.HeroRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject


fun Route.getAllHeroes() {

    val heroRepository by inject<HeroRepository>()

    get("/boruto/heroes") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..5)

            val apiResponse = heroRepository.getAllHeroes(page = page)
            call.respond(message = apiResponse,status = HttpStatusCode.OK)

            call.respond(message = page)
        } catch (e : NumberFormatException){
            call.respond(
                message = ApiResponse(success = false, message = "Only numbers allows"),
                status = HttpStatusCode.BadRequest
            )
        } catch (e : java.lang.IllegalArgumentException){
            call.respond(
                message = ApiResponse(success = false, message = "Heroes not found"),
                status = HttpStatusCode.NotFound
            )
        }
    }
}