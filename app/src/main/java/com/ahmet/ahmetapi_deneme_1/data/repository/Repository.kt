package com.ahmet.ahmetapi_deneme_1.data.repository


import androidx.paging.PagingData
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.repository.DataStoreOparations
import com.ahmet.ahmetapi_deneme_1.domain.repository.LocalDataSource
import com.ahmet.ahmetapi_deneme_1.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val local:LocalDataSource ,
    private val remote: RemoteDataSource,
    private val dataStore : DataStoreOparations
){

    fun getAllHeroes(): Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }

    fun searchHeroes(query: String): Flow<PagingData<Hero>>{
        return remote.searchHeroes(query = query)
    }

    suspend fun getSelectedHero(heroId: Int): Hero{
        return local.getSelectedHero(heroId = heroId)
    }

    suspend fun saveOnBoardingState(completed: Boolean){
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readOnBoardingState(): Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }
}