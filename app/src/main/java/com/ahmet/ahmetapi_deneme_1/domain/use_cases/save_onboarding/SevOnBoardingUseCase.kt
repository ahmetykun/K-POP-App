package com.ahmet.ahmetapi_deneme_1.domain.use_cases.save_onboarding

import com.ahmet.ahmetapi_deneme_1.data.repository.Repository

class SevOnBoardingUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(completed: Boolean){
        repository.saveOnBoardingState(completed = completed)

    }
}