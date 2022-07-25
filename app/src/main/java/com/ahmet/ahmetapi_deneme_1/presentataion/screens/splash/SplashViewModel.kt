package com.ahmet.ahmetapi_deneme_1.presentataion.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCase: UseCases
):ViewModel() {

    private val _onBoardingCompoleted = MutableStateFlow(false)
    val onBoardingCompoleted: StateFlow<Boolean> =_onBoardingCompoleted

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _onBoardingCompoleted.value=
                useCase.readOnBoardingCase().stateIn(viewModelScope).value
        }
    }
}