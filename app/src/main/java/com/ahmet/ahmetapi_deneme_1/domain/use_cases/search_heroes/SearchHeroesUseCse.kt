package com.ahmet.ahmetapi_deneme_1.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.ahmet.ahmetapi_deneme_1.data.repository.Repository
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCse(
    private val repository: Repository
) {
    operator fun invoke( query:String): Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }
}