package udemy.boruto.compose.myapplication.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import udemy.boruto.compose.myapplication.data.local.database.HeroDatabase
import udemy.boruto.compose.myapplication.utils.Constraint.HERO_DATABASE
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): HeroDatabase{
     return Room.databaseBuilder(
         context,
         HeroDatabase::class.java,
         HERO_DATABASE
     ).build()
    }
}