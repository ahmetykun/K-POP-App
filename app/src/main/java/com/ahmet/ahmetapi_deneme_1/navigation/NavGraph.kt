package com.ahmet.ahmetapi_deneme_1.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.details.DetailsScreen
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.home.HomeScreen
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.searh.SearchScreen
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.splash.SplashScreen
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.welcome.WelcomeScreen
import com.ahmet.ahmetapi_deneme_1.util.Constants.DETAILS_ARGUMENT_KEY
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
        ){
        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.Walcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.Details.route,
        arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
                type = NavType.IntType
            } )
        ) {
            DetailsScreen(navController = navController)
        }
        composable(route = Screen.Search.route) {
            SearchScreen(navController = navController)
        }

    }
}