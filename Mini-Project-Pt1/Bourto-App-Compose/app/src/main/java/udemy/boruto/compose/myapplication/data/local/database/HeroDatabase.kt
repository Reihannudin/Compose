package udemy.boruto.compose.myapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import udemy.boruto.compose.myapplication.data.local.dao.HeroDao
import udemy.boruto.compose.myapplication.data.local.dao.HeroRemoteKeysDao
import udemy.boruto.compose.myapplication.domain.models.Hero
import udemy.boruto.compose.myapplication.domain.models.HeroRemoteKeys
import udemy.boruto.compose.myapplication.utils.Converter

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 2)
@TypeConverters(Converter::class)

abstract class HeroDatabase : RoomDatabase(){

    abstract fun heroDao() : HeroDao
    abstract fun heroRemoteKeyDao() : HeroRemoteKeysDao

}