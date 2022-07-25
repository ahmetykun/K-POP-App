package com.ahmet.ahmetapi_deneme_1.data.repository

import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.repository.LocalDataSource

class LocalDataSourceImp(ahmetDatabase: AhmetDatabase): LocalDataSource {

    private val heroDao = ahmetDatabase.heroDao()


    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}