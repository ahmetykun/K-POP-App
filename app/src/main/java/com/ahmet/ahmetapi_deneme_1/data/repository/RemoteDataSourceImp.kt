package com.ahmet.ahmetapi_deneme_1.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.data.paging_source.HeroRemoteMediator
import com.ahmet.ahmetapi_deneme_1.data.paging_source.SearchHeroesSource
import com.ahmet.ahmetapi_deneme_1.data.remote.AhmetApi
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.repository.RemoteDataSource
import com.ahmet.ahmetapi_deneme_1.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class RemoteDataSourceImp(
    private val ahmetApi :AhmetApi,
    private val ahmetDatabase: AhmetDatabase
): RemoteDataSource {

    private val heroDao = ahmetDatabase.heroDao()


    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHeroes() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                ahmetApi = ahmetApi,
                ahmetDatabase = ahmetDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(query: String): Flow<PagingData<Hero>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchHeroesSource(ahmetApi = ahmetApi, query = query)
            }
        ).flow
    }
}
