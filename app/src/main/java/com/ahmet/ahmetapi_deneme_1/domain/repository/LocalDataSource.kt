package com.ahmet.ahmetapi_deneme_1.domain.repository

import com.ahmet.ahmetapi_deneme_1.domain.model.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}