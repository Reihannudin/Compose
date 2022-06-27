package udemy.boruto.compose.myapplication.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import udemy.boruto.compose.myapplication.domain.models.Hero

@Dao
interface HeroDao {

    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    fun getALlHeroes() : PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE id=:heroId")
    fun getSelectedHero(heroId : Int) : Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroes(heroes : List<Hero>)

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()

}