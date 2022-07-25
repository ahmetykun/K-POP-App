package com.ahmet.ahmetapi_deneme_1.presentataion.screens.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WecomeViewModel @Inject constructor(
    private val useCases: UseCases
):ViewModel(){

    fun saveOnBoardingState(completed: Boolean){
        viewModelScope.launch(Dispatchers.IO){
        useCases.seveOnBoardingCase(completed = completed)
        }
    }
}