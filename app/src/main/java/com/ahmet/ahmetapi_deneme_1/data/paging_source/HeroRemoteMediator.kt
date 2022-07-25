package com.ahmet.ahmetapi_deneme_1.data.paging_source

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.data.remote.AhmetApi
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.model.HeroRemoteKeys
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.math.log

@ExperimentalPagingApi
class HeroRemoteMediator (
    private val ahmetApi: AhmetApi,
    private val ahmetDatabase: AhmetDatabase,
) : RemoteMediator<Int,  Hero>() {

    private val heroDao = ahmetDatabase.heroDao()
    private val heroRemoteKeyDao =ahmetDatabase.heroRemoteKeysDao()


    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeyDao.getRemoteKeys(heroId = 1)?.lastUpdated ?: 0L
        val cacheTimeout = 1440

       // Log.d("RemoteMediator", "Current Time: ${parseMillis(currentTime)}")
      //  Log.d("RemoteMediator", "Last Updated Time: ${parseMillis(lastUpdated)}")

        val diffInMinutes = (currentTime - lastUpdated) / 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout) {
            Log.d("RemoteMediator", "Up To Data!")
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            Log.d("RemoteMediator", "REFRESH!")
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
     return  try {
        val page = when(loadType){
            LoadType.REFRESH -> {
                 val remoteKeys = getRemoteKeyClosesToCurrentPositions(state)
                 remoteKeys?.nextPage?.minus(1) ?: 1

            }
            LoadType.PREPEND -> {
                val remoteKeys = getremoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevPage
                    ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                prevPage
            }
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyForLastItem(state)
                val nextPage = remoteKey?.nextPage
                    ?:return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                nextPage
            }
        }

         val  response = ahmetApi.getAllHeroes(page = page)
         if (response.heroes.isNotEmpty()){
               ahmetDatabase.withTransaction {
                   if (loadType == LoadType.REFRESH){
                       heroDao.deleteAllHeroes()
                        heroRemoteKeyDao.deleteAllRemoteKeys()
                   }
                   val prevPage = response.prevPage
                   val nextPage =   response.nextPage
                   val keys = response.heroes.map { hero ->
                       HeroRemoteKeys(
                           id = hero.id,
                           prevPage = prevPage,
                           nextPage = nextPage,
                           lastUpdated = response.lastUpdated
                       )
                   }
                   heroRemoteKeyDao.addAllRemoteKeys(heroRemoteKeys =  keys)
                   heroDao.addHeroes(heroes = response.heroes)
               }

           }
           MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
       }catch (e: Exception){
           return MediatorResult.Error(e)
       }
    }

    private suspend fun getRemoteKeyClosesToCurrentPositions(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeyDao.getRemoteKeys(heroId = id)
            }
        }
    }

    private suspend fun getremoteKeyForFirstItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.firstOrNull{ it.data.isNotEmpty()}?.data?.firstOrNull()
            ?.let { hero ->
            heroRemoteKeyDao.getRemoteKeys(heroId = hero.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Hero>
    ): HeroRemoteKeys? {
        return state.pages.lastOrNull{ it.data.isNotEmpty()}?.data?.lastOrNull()
            ?.let { hero ->
                heroRemoteKeyDao.getRemoteKeys(heroId = hero.id)
            }
    }

 //   private fun parseMillis(millis: Long): String {
 //       val date = Date(millis)
  //      val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.ROOT)
  //      return format.format(date)
  //  }

}















