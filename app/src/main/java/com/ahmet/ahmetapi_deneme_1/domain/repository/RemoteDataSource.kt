package com.ahmet.ahmetapi_deneme_1.domain.repository

import androidx.paging.PagingData
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(query: String): Flow<PagingData<Hero>>
}