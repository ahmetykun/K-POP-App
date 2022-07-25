package com.ahmet.ahmetapi_deneme_1.presentataion.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.navigation.Screen
import com.ahmet.ahmetapi_deneme_1.ui.theme.Purple500
import com.ahmet.ahmetapi_deneme_1.ui.theme.Purple700
import com.ahmet.ahmetapi_deneme_1.ui.theme.YeniCOLOR

@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel= hiltViewModel()
){
    val onBoardingCompleted by splashViewModel.onBoardingCompoleted.collectAsState()

    val degrees = remember { androidx.compose.animation.core.Animatable(0f) }

    LaunchedEffect(key1 = true){
        degrees.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
       if (onBoardingCompleted){
           navController.navigate(Screen.Home.route)
       }else{
           navController.navigate(Screen.Walcome.route)
       }
    }

    Splash(degrees = degrees.value)
}
@Composable
fun Splash(degrees: Float) {

    var  modifier = if (isSystemInDarkTheme()) {
        Modifier.background(Color.Black)
    } else { Modifier.background(
        Brush.verticalGradient(listOf(Purple700, Purple500))
        )
    }


    if (isSystemInDarkTheme()){
        Box(modifier = Modifier
            .background(Color.Black)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier =Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )

        }
    }else{
        Box(modifier = Modifier
            .background(Brush.verticalGradient(listOf(YeniCOLOR, Purple500)))
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier =Modifier.rotate(degrees = degrees),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.app_logo)
            )


        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(degrees = 0f)

}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Splash(degrees = 0f)
}