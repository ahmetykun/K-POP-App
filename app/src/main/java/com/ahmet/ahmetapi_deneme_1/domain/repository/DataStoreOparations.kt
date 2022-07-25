package com.ahmet.ahmetapi_deneme_1.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOparations {
    suspend fun saveOnBoardingState(completed: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
}