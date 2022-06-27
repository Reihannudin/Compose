package udemy.boruto.compose.myapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import udemy.boruto.compose.myapplication.utils.Constraint.HERO_TABLE_DATABASE_NAME

@Serializable
@Entity(tableName = HERO_TABLE_DATABASE_NAME)
data class Hero(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image: String,
    val about: String,
    val rating: Double,
    val power: Int,
    val month: String,
    val day: String,
    val family: List<String>,
    val abilities: List<String>,
    val natureTypes: List<String>
)
