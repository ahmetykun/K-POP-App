package com.ahmet.ahmetapi_deneme_1.domain.use_cases.read_onboarding

import com.ahmet.ahmetapi_deneme_1.data.repository.Repository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Boolean>{
        return repository.readOnBoardingState()
    }
}