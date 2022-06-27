package udemy.boruto.compose.myapplication.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import udemy.boruto.compose.myapplication.data.local.database.HeroDatabase
import udemy.boruto.compose.myapplication.data.pagging_source.HeroRemoteMediator
import udemy.boruto.compose.myapplication.data.remote.BorutoApi
import udemy.boruto.compose.myapplication.domain.models.Hero
import udemy.boruto.compose.myapplication.domain.repository.RemoteDataSource
import udemy.boruto.compose.myapplication.utils.Constraint.ITEM_PER_PAGE

@ExperimentalPagingApi
class RemoteDataSourceImpl(
    private val borutoApi: BorutoApi,
    private val heroDatabase: HeroDatabase
) : RemoteDataSource {
    private val heroDao = heroDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = {heroDao.getALlHeroes()}
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi =borutoApi,
                heroDatabase = heroDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }

}