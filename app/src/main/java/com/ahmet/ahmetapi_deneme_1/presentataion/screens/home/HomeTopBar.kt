package com.ahmet.ahmetapi_deneme_1.presentataion.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.ui.theme.topAppARAMAbar
import com.ahmet.ahmetapi_deneme_1.ui.theme.topAppARAMAbarbacgraunt

@Composable
fun HomeTopBar(onSearchClickes: () -> Unit){
    TopAppBar(
        title = {
        Text(
            text = "Arama",
            color = MaterialTheme.colors.topAppARAMAbar
        )
        },
            backgroundColor =MaterialTheme.colors.topAppARAMAbarbacgraunt,
            actions = {
                IconButton(onClick =onSearchClickes) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon)
                    )

                }
            }
    )
}

@Composable
@Preview
fun HomeTopBarPreview(){
    HomeTopBar {

    }
}
