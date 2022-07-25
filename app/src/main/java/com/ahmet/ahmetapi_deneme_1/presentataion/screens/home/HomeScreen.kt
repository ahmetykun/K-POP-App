package com.ahmet.ahmetapi_deneme_1.presentataion.screens.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.ahmet.ahmetapi_deneme_1.navigation.Screen
import com.ahmet.ahmetapi_deneme_1.presentataion.common.ListContent
import com.ahmet.ahmetapi_deneme_1.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){
    val allHeroes= homeViewModel.getAllHeroes.collectAsLazyPagingItems()



    val systemUiController = rememberSystemUiController()
    val systemBarColor=MaterialTheme.colors.statusBarColor

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
    }


    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClickes = {
                    navController.navigate(Screen.Search.route)

               }
            )
         },
        content = {
            ListContent(
                heroes = allHeroes,
                nacController = navController
            )
        }
    )

}