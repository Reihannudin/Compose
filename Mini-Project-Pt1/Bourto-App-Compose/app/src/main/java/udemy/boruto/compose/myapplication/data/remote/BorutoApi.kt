package udemy.boruto.compose.myapplication.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import udemy.boruto.compose.myapplication.domain.models.ApiResponse

interface BorutoApi {

    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page")  page : Int = 1
    ) : ApiResponse

    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name : String
    ) : ApiResponse
}