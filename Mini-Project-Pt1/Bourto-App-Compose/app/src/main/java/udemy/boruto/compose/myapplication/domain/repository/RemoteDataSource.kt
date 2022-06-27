package udemy.boruto.compose.myapplication.domain.repository

import androidx.paging.PagingData
import androidx.paging.PagingSource
import kotlinx.coroutines.flow.Flow
import udemy.boruto.compose.myapplication.domain.models.Hero

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query : String) : Flow<PagingData<Hero>>
}