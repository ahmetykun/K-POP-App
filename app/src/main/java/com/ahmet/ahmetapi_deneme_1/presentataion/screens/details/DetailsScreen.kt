package com.ahmet.ahmetapi_deneme_1.presentataion.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.ahmet.ahmetapi_deneme_1.util.Constants.BASE_URL
import com.ahmet.ahmetapi_deneme_1.util.PelletGenerator.convertImageUrlToBitmap
import com.ahmet.ahmetapi_deneme_1.util.PelletGenerator.extracColorsFromBitmap
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navController: NavHostController,
    detailsViewModel: DatailsViewModel = hiltViewModel()
){
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()){
        DatailsContent(
            navController = navController ,
            selectedHero = selectedHero,
            colors = colorPalette,

        )
    }else{
        detailsViewModel.generatorColorPalette()
    }
    val context = LocalContext.current
    LaunchedEffect(key1 =true  ){
        detailsViewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.GeneratorColorPalette -> {
                    val bitmap =convertImageUrlToBitmap(
                        imageUrl = "$BASE_URL${selectedHero?.image}",
                        content = context
                    )
                    if (bitmap !=null){
                        detailsViewModel.setColorPalette(
                            colors = extracColorsFromBitmap(
                                bitmap = bitmap
                            )
                        )
                    }
                }
            }
        }
    }
}

