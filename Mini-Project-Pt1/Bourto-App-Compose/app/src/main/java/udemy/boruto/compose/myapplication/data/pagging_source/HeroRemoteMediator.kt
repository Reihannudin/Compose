@file:OptIn(ExperimentalPagingApi::class)

package udemy.boruto.compose.myapplication.data.pagging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import udemy.boruto.compose.myapplication.data.local.database.HeroDatabase
import udemy.boruto.compose.myapplication.data.remote.BorutoApi
import udemy.boruto.compose.myapplication.domain.models.Hero
import udemy.boruto.compose.myapplication.domain.models.HeroRemoteKeys
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val heroDatabase : HeroDatabase
) : RemoteMediator<Int, Hero>() {

    private val heroDao = heroDatabase.heroDao()
    private val heroRemoteKeysDao = heroDatabase.heroRemoteKeyDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdate = heroRemoteKeysDao.getRemoteKey(id = 1)?.lastUpdate ?: 0L
        val cacheTimeOut = 1440
        Log.d("RemoteMediator", " Current Time : ${parseMillis(currentTime)} ")
        Log.d("RemoteMediator", " Last Update Time : ${parseMillis(lastUpdate)}")

        val diffInMinutes = (currentTime - lastUpdate) / 1000 / 60
        return if(diffInMinutes.toInt() <= cacheTimeOut){
            Log.d("RemoteMediator", "UPDATE TIME")
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            Log.d("RemoteMediator", "REFRESH TIME")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {

            val page = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeysCloseToCurrenctPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()) {
                heroDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdate = response.lastUpdate
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeysCloseToCurrenctPosition(
        state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKey(id = id)
            }
        }

    }

    private suspend fun getRemoteKeysForFirstItem(
        state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                heroRemoteKeysDao.getRemoteKey(id = it.id)
            }

    }

    private suspend fun getRemoteKeysForLastItem(
        state: PagingState<Int, Hero>): HeroRemoteKeys? {
        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                heroRemoteKeysDao.getRemoteKey(id = it.id)
            }

    }

    private fun parseMillis(millis : Long): String{
        val date = Date(millis)
        val format = SimpleDateFormat("yyyy.MM.dd HH.mm", Locale.ROOT)
        return format.format(date)
    }

}


