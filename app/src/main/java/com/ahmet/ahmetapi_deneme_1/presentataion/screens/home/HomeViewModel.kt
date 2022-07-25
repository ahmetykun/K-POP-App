package com.ahmet.ahmetapi_deneme_1.presentataion.screens.home

import androidx.lifecycle.ViewModel
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {
    val getAllHeroes = useCases.getAllHeroesUseCase()

}