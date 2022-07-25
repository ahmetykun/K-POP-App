package com.ahmet.ahmetapi_deneme_1.data.remote

import com.ahmet.ahmetapi_deneme_1.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AhmetApi {

    @GET("/ahmet/heroes")
    suspend fun getAllHeroes(
        @Query("page") page : Int = 1
    ): ApiResponse

    @GET("/ahmet/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name: String
    ): ApiResponse
}