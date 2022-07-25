package com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_selected_hero

import com.ahmet.ahmetapi_deneme_1.data.repository.Repository
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(heroId: Int):Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}