package com.ahmet.ahmetapi_deneme_1.domain.use_cases

import com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.save_onboarding.SevOnBoardingUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.search_heroes.SearchHeroesUseCse

data class UseCases(
    val seveOnBoardingCase: SevOnBoardingUseCase,
    val readOnBoardingCase: ReadOnBoardingUseCase,
    val getAllHeroesUseCase: GetAllHeroesUseCase,
    val searchHeroesUseCase: SearchHeroesUseCse,
    val getSelectedHeroUseCase: GetSelectedHeroUseCase
)