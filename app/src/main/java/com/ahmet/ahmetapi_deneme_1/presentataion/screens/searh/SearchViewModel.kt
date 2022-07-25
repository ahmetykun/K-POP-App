package com.ahmet.ahmetapi_deneme_1.presentataion.screens.searh

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedHeroes = MutableStateFlow<PagingData<Hero>> (PagingData.empty())
    val searchHeroes = _searchedHeroes

    fun updateSearchQuery (query: String){
        _searchQuery.value = query
    }

    fun searchHeroes(query: String){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchHeroesUseCase(query =query ).cachedIn(viewModelScope).collect {
                _searchedHeroes.value = it
            }
        }
    }
}