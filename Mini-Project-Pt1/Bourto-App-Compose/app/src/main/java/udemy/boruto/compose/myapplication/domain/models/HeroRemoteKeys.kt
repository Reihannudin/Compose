package udemy.boruto.compose.myapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import udemy.boruto.compose.myapplication.utils.Constraint.HERO_REMOTE_KEYS_TABLE_DATABASE

@Entity(tableName = HERO_REMOTE_KEYS_TABLE_DATABASE)
data class HeroRemoteKeys (
    @PrimaryKey(autoGenerate = false)
    val id : Int,
    val prevPage : Int?,
    val nextPage : Int?,
    val lastUpdate : Long?
    )