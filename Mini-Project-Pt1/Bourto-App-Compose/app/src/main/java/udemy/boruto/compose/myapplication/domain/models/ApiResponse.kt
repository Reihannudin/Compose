package udemy.boruto.compose.myapplication.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse (
    val success : Boolean,
    val message : String? = null,
    val prevPage : Int? = null,
    var nextPage : Int? = null,
    val heroes : List<Hero> = emptyList(),
    val lastUpdate : Long? = null
)