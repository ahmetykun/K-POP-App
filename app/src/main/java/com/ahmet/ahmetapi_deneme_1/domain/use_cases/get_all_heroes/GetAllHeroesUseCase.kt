package com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.ahmet.ahmetapi_deneme_1.data.repository.Repository
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}