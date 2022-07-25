package com.ahmet.ahmetapi_deneme_1.presentataion.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.domain.model.OnBoardingPage
import com.ahmet.ahmetapi_deneme_1.navigation.Screen
import com.ahmet.ahmetapi_deneme_1.ui.theme.*
import com.ahmet.ahmetapi_deneme_1.util.Constants.LAST_ON_BOARDING_PAGE
import com.ahmet.ahmetapi_deneme_1.util.Constants.ON_BOARDING_PAGE_COUNT
import com.google.accompanist.pager.*

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    navController: NavController,
    welcomeViewModel: WecomeViewModel = hiltViewModel()
    ){

    val pages = listOf(
    OnBoardingPage.First,
    OnBoardingPage.Second,
    OnBoardingPage.Trirh
    )

    val pagerState = rememberPagerState()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            modifier= Modifier.weight(10f),
            state =pagerState,
            count = ON_BOARDING_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) {position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier= Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
            ,
            pagerState =pagerState,
            activeColor =MaterialTheme.colors.activeIndicotorColor,

            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING
        )
        FinisButton(
            modifier =Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack()
            navController.navigate(Screen.Home.route)
            welcomeViewModel.saveOnBoardingState(completed = true)

        }
    }
}


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage){
    Column(

        modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

    ) {
        Image(
            modifier= Modifier
                .fillMaxWidth(0.5f)
                .fillMaxHeight(0.7f),
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = stringResource(R.string.on_boarding_images)
        )
        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(top = EXTRA_LARGE_PADDING),
            text = onBoardingPage.title,
            color =MaterialTheme.colors.titleColor,
            fontSize = MaterialTheme.typography.h4.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier= Modifier
                .fillMaxWidth()
                .padding(horizontal = EXTRA_LARGE_PADDING)
                .padding(top = SMALL_PADDING),
            text = onBoardingPage.description,
        color =MaterialTheme.colors.descriptionColor,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center
        )
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinisButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(modifier = modifier
        .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center,

    ) {

        AnimatedVisibility(
             modifier = Modifier.fillMaxWidth() ,
            visible = pagerState.currentPage == LAST_ON_BOARDING_PAGE

        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =MaterialTheme.colors.bottomColor,
                    contentColor = Color.White,
                    )
            ) {
                Text(text = "Finish")
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
        
    }
}

@Composable
@Preview(showBackground = true)
fun SecoundOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)

    }
}
@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Trirh)

    }
}