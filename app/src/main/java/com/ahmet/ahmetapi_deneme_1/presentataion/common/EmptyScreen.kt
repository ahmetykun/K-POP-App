package com.ahmet.ahmetapi_deneme_1.presentataion.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.paging.LoadState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.ui.theme.DarkGray
import com.ahmet.ahmetapi_deneme_1.ui.theme.LightGray
import com.ahmet.ahmetapi_deneme_1.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.ahmet.ahmetapi_deneme_1.ui.theme.SMALL_PADDING
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
    error: LoadState.Error? = null,
    heroes: LazyPagingItems<Hero>? =null
){
    var message by remember {
        mutableStateOf("Find your Favorite hero!")
    }
    var icon by remember {
       mutableStateOf(R.drawable.ic_arama_bulunamad_)
    }

if (error != null){
    message = parseErrorMassage(error = error)
    icon = R.drawable.ic_internet_yok
}

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation = true
    }
EmptyContent(
    alphaAnim = alphaAnim,
    icon = icon,
    message = message,
    heroes = heroes,
    error = error
)

}
@Composable
fun EmptyContent(
    alphaAnim: Float,
    icon: Int,
    message: String,
    heroes: LazyPagingItems<Hero>? =null,
    error: LoadState.Error? = null,
){
    var isRefreshing by remember{ mutableStateOf(false) }


  SwipeRefresh(
      swipeEnabled = error != null,
      state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
      onRefresh = {
            isRefreshing = true
            heroes?.refresh()
            isRefreshing = false
      }
  ) {
      Column(
          modifier = Modifier
              .fillMaxSize()
              .verticalScroll(rememberScrollState()),
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {
          Icon(
              modifier = Modifier
                  .size(NETWORK_ERROR_ICON_HEIGHT)
                  .alpha(alpha = alphaAnim),
              painter = painterResource(id = icon),
              contentDescription = stringResource(R.string.network_error_icon),
              tint = if (isSystemInDarkTheme()) LightGray else DarkGray
          )
          Text(
              modifier = Modifier
                  .padding(top = SMALL_PADDING)
                  .alpha(alpha = alphaAnim),
              text = message,
              color = if (isSystemInDarkTheme()) LightGray else DarkGray,
              textAlign = TextAlign.Center,
              fontWeight = FontWeight.Medium,
              fontSize = MaterialTheme.typography.subtitle1.fontSize
          )
      }
  }
}



fun parseErrorMassage(error: LoadState.Error): String{

    return when (error.error) {
        is SocketTimeoutException -> {
            "Servera Bağlanılamıyor.."
        }
        is ConnectException -> {
            "İnternete Bağlanılamıyor."
        }
        else -> {
            "Bilinmeyen Hata."
        }
    }
}




@Composable
@Preview(showBackground = true)
fun EmtyScreenPreview() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.ic_internet_yok,
        message = "İnternete Bağlanılamıyor."
    )
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun EmtyScreenDARKPreview() {
    EmptyContent(
        alphaAnim = ContentAlpha.disabled,
        icon = R.drawable.ic_internet_yok,
        message = "İnternete Bağlanılamıyor."
    )
}

















