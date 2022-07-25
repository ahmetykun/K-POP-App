package com.ahmet.ahmetapi_deneme_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.ahmet.ahmetapi_deneme_1.navigation.SetupNavGraph
import com.ahmet.ahmetapi_deneme_1.ui.theme.AhmetAPI_deneme_1Theme
import com.example.comptest.rememberWindowSize
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint

class MainActivity : ComponentActivity() {

private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AhmetAPI_deneme_1Theme {

                navController = rememberNavController()
                SetupNavGraph(navController = navController)

            }
        }
    }
}
