package com.ahmet.ahmetapi_deneme_1.presentataion.screens.searh

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.ahmet.ahmetapi_deneme_1.presentataion.common.ListContent
import com.ahmet.ahmetapi_deneme_1.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun SearchScreen(
        navController: NavController,
        searchViewModel: SearchViewModel = hiltViewModel()

) {
val searchQuery by searchViewModel.searchQuery
val heroes = searchViewModel.searchHeroes.collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    val systemBarColor =MaterialTheme.colors.statusBarColor

   SideEffect {

       systemUiController.setStatusBarColor(
           color = systemBarColor
       )

   }

    Scaffold(
        topBar = {
            SearchTopBar(
                text = searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                },
                onSearchClicked = {
                    searchViewModel.searchHeroes(query = it)
                },
                onCloseClick = {
                    navController.popBackStack()
                }
            )
        },
        content = {
            ListContent(heroes = heroes, nacController = navController)

        }
    )
}