package com.ktor.endpoint

import com.ktor.models.ApiResponse
import com.ktor.module
import com.ktor.repository.HeroRepository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.koin.java.KoinJavaComponent.inject
import kotlin.test.assertEquals


class RootEndpointTest {

    private val heroRepository: HeroRepository by inject(HeroRepository::class.java)

    @Test
    fun `access home root endpoint, assert correct information`(){
        withTestApplication(moduleFunction = Application::module){
            handleRequest(HttpMethod.Get,"/").apply { 
                assertEquals(
                    expected = "Hello, Army!",
                    actual = response.content
                )
                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = response.status()
                )
            }
        }
    }


    @ExperimentalSerializationApi
    @Test
    fun `access heroes root endpoint, assert correct information`(){
        withTestApplication(moduleFunction = Application::module){
            handleRequest(HttpMethod.Get,"/boruto/heroes?page=1").apply {
                val expected = ApiResponse(
                    success = true,
                    message = "ok",
                    prevPage = null,
                    nextPage = 2,
                    heroes = heroRepository.page1)
                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
                assertEquals(
                    expected = expected,
                    actual = actual
                )
                assertEquals(
                    expected = HttpStatusCode.OK,
                    actual = response.status()
                )
            }
        }
    }

}

/*
* //                val expected = ApiResponse(
//                    success = true,
//                    message = "ok",
//                    prevPage = null,
//                    nextPage = 2,
//                    heroes = heroRepository.page1)
//                val actual = Json.decodeFromString<ApiResponse>(response.content.toString())
//                assertEquals(
//                    expected = expected,
//                    actual = actual
//                )
* */