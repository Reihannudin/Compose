package udemy.boruto.compose.myapplication.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import udemy.boruto.compose.myapplication.domain.models.Hero
import udemy.boruto.compose.myapplication.domain.repository.DataStoreOperations
import udemy.boruto.compose.myapplication.domain.repository.RemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStore : DataStoreOperations,
    private val remote : RemoteDataSource
) {

    fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }

    suspend fun saveOnBoardingState(completed : Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState() : Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }
}