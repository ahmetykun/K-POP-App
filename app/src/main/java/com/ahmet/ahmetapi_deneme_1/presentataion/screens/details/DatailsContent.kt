package com.ahmet.ahmetapi_deneme_1.presentataion.screens.details

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.presentataion.component.InfoBox
import com.ahmet.ahmetapi_deneme_1.presentataion.component.OrderList
import com.ahmet.ahmetapi_deneme_1.ui.theme.*
import com.ahmet.ahmetapi_deneme_1.util.Constants.ABOUT_TEXT_MAX_LINE
import com.ahmet.ahmetapi_deneme_1.util.Constants.BASE_URL
import com.ahmet.ahmetapi_deneme_1.util.Constants.MIN_BACGRAUND_IMAGE
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DatailsContent(
    navController: NavController,
    selectedHero: Hero?,
    colors: Map<String, String>,

    ///////////

) {



    /////////////////
    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#ffffff") }

    LaunchedEffect(key1 = selectedHero) {
        vibrant = colors["vibrant"]!!
        darkVibrant = colors["darkVibrant"]!!
        onDarkVibrant = colors["onDarkVibrant"]!!
    }

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(parseColor(darkVibrant))
        )
    }

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Expanded)
    )

    val currentSheetFraction = scaffoldState.currentSheetFraction

    val radiusAnim by animateDpAsState(
        targetValue =
        if (currentSheetFraction == 1f)
            EXTRA_LARGE_PADDING
        else
            EXPANDED_RAIUS_LEVEL
    )



    BottomSheetScaffold(
        sheetShape = RoundedCornerShape(
            topStart = radiusAnim,
            topEnd = radiusAnim
        ),
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = {
            selectedHero?.let {
                BottomSheetContent(
                    selectedHero = it,
                    infoBoxIconColor = Color(parseColor(vibrant)),
                    sheetBackgroundColor = Color(parseColor(darkVibrant)),
                    contentColor = Color(parseColor(onDarkVibrant)),


                )
            }
        },
        content = {
            selectedHero?.let { hero ->
                BackgroundContent(
                    heroImage = hero.image,
                    imageFraction = currentSheetFraction,
                    backgraundColor = Color(parseColor(darkVibrant)),
                    onCloseClicked = {
                        navController.popBackStack()
                    }
                )
            }
        }
    )
}


@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor,


///////
    modifier: Modifier = Modifier,
    text: String = selectedHero.about,
///////
    )
{
////////
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    var finalText by remember { mutableStateOf(text) }

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText = "$text ...Yazıyı Kısalt"
            }
            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(ABOUT_TEXT_MAX_LINE - 1)
                val showMoreString = " ...Devamını Gör"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreString.length)
                    .dropLastWhile { it == ' ' || it == '.'

                    }

                finalText = "$adjustedText$showMoreString"

                isClickable = true
            }
        }
    }
    //////////////////
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {


            Icon(
                modifier = Modifier
                    .size(INFO_ICON_SIZE)
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_nft_tag),
                contentDescription = stringResource(id = R.string.app_logo),

                )
            Text(
                modifier = Modifier
                    .weight(6f),
                text = selectedHero.name,
                color = contentColor,
                fontSize = MaterialTheme.typography.h4.fontSize,
                fontWeight = FontWeight.Bold
            )
            ////
            Icon(
                modifier = Modifier
                    .size(NAME_PLACEHOLDER_HEGHT)
                    .weight(1f),
                painter = painterResource(id = R.drawable.down),
                contentDescription = stringResource(id = R.string.down)

            )
            ////
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            InfoBox(
                icon = painterResource(id = R.drawable.ic_simsek),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = stringResource(R.string.power),
                textColor = contentColor
            )

            InfoBox(
                icon = painterResource(id = R.drawable.ic_takvim),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.month,
                smallText = stringResource(R.string.ay),
                textColor = contentColor
            )
            InfoBox(
                icon = painterResource(id = R.drawable.ic_dogumgunupastasi),
                iconColor = infoBoxIconColor,
                bigText = selectedHero.day,
                smallText = stringResource(R.string.gün),
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.about),
            color = contentColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold

        )
  ///////////////////////////////////////////////
        Text(
            text = finalText,
            maxLines = if (isExpanded) Int.MAX_VALUE else ABOUT_TEXT_MAX_LINE,
            onTextLayout = { textLayoutResultState.value = it },
            modifier = modifier
                .clickable(enabled = isClickable) { isExpanded = !isExpanded }
                .animateContentSize()
                .alpha(ContentAlpha.medium)
                .padding(bottom = MEDIUM_PADDING),
            color = contentColor,
            fontSize = MaterialTheme.typography.body1.fontSize,
        )

        //////////////////////////////////////1.yöntemi///////////
//        Text(
//            modifier = Modifier
//                .alpha(ContentAlpha.medium)
//                .padding(bottom = MEDIUM_PADDING),
//            text = selectedHero.about,
//            color = contentColor,
//            fontSize = MaterialTheme.typography.body1.fontSize,
//
//        )

////////2. yöntemm//////////////////////////

//        var showMore by remember {
//            mutableStateOf(false)
//        }
//        var text = selectedHero.about
//        Column(
//            modifier = Modifier
//                .padding(1.dp)
//                .alpha(ContentAlpha.medium)
//                .padding(bottom = MEDIUM_PADDING),
//        ) {
//            Column(
//                modifier = Modifier
//                    .animateContentSize(animationSpec = tween(100))
//                    .clickable(
//                        interactionSource = remember { MutableInteractionSource() },
//                        indication = null
//                    )
//
//                    { showMore = !showMore })
//
//            {
//
//                if (showMore) {
//                    Text(text = text,color = contentColor, fontSize = MaterialTheme.typography.body1.fontSize,)
//
//                } else {
//                    Text(text = text, maxLines = 3, overflow = TextOverflow.Ellipsis,color = contentColor, fontSize = MaterialTheme.typography.body1.fontSize)
//                }
//            }
//        }

//////////////////////////////////
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OrderList(
                title = stringResource(R.string.family),
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderList(
                title = stringResource(R.string.abilities),
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderList(
                title = stringResource(R.string.nature_types),
                items = selectedHero.natureTypes,
                textColor = contentColor
            )


        }
    }
}

@ExperimentalCoilApi
@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgraundColor: Color = MaterialTheme.colors.surface,
    onCloseClicked: () -> Unit

) {
    val imageUrl = "$BASE_URL${heroImage}"
//    val painter = rememberImagePainter(imageUrl){
//        error(R.drawable.ic_launcher_background)
//    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgraundColor)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = imageFraction + MIN_BACGRAUND_IMAGE)
                .align(Alignment.TopStart),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = imageUrl)
                .error(drawableResId = R.drawable.ic_olumsuz_ekran)
                .build(),
            contentDescription = stringResource(id = R.string.hero_image),
            contentScale = ContentScale.Crop
        )
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .fillMaxHeight(fraction = imageFraction + MIN_BACGRAUND_IMAGE)
//                    .align(Alignment.TopStart)  ,
//                painter =painter ,
//                contentDescription = stringResource(id = R.string.hero_image),
//                contentScale = ContentScale.Crop
//            )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(
                modifier = Modifier.padding(all = SMALL_PADDING),
                onClick = { onCloseClicked() }
            ) {
                Icon(
                    modifier = Modifier.size(INFO_ICON_SIZE),
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = Color.White
                )
            }
        }
    }
}

@ExperimentalMaterialApi
val BottomSheetScaffoldState.currentSheetFraction: Float
    get() {
        val fraction = bottomSheetState.progress.fraction
        val targetValue = bottomSheetState.targetValue
        val currentValue = bottomSheetState.currentValue

        Log.d("Fraction", fraction.toString())
        Log.d("Fraction Target", targetValue.toString())
        Log.d("Fraction Current", currentValue.toString())

        return when {
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Collapsed -> 1f
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Expanded -> 0f
            currentValue == BottomSheetValue.Collapsed && targetValue == BottomSheetValue.Expanded -> 1f - fraction
            currentValue == BottomSheetValue.Expanded && targetValue == BottomSheetValue.Collapsed -> 0f + fraction
            else -> fraction
        }
    }


@Composable
@Preview
fun BottomSheetContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Sasuke",
            image = "",
            about = "Kendi Haritanızı Oluşturun Kendinize ait haritalar oluşturarak farklı planları hızlandırabilirsiniz. Oluşturacağınız harita ile gideceğiniz lokasyonu tekrar aramanıza gerek kalmadan haritayı tıklamanız yeterli olacaktır. Kendinize özel haritalar oluşturabilmek için uygulamanın sol köşedeki menü düğmesine (üç çizgi), ardından Yerleriniz'e basın. Bu sekmeden Haritalar'a gidin ve alttaki Harita Oluştur'a basın (veya daha önce yapmış olduğunuz bir haritaya tıklayın). Bu araç, özellikle daha önce hiç gitmediğiniz bölgeleri keşfetmenize yarayacak. Herhangi bir gezi ya da tatil planınız varsa haritaya işaret yerleştirebilir ve farklı gruplara ayırabilirsiniz. Böylece zamanınızı en iyi şekilde değerlendirebilirsiniz. Arkadaşlarınızla ya da ailenizle gideceğiniz bir tatile ortak çalışan ekleyerek 'paylaş' seçeneğini kullanabilirsiniz. Bu şekilde harita üzerinde tatil planında her katılımcı ilginç görülen yerleri işaretleyebilir ve herkesin görmesini sağlayabilir.",
            rating = 15.0,
            power = 98,
            month = "Mayıs",
            day = "2013",
            family = listOf(
                "Fugaku", "Mikoto", "Itachi", "Sarada", "Sakura", "Ahmet", "Mehmet"
            ),
            abilities = listOf(
                "Sharingan", "Rinnegan", "Sussano", "Amateratsu", "Intelligence"
            ),
            natureTypes = listOf("Lightning", "Fire", "Wind", "Earth", "Water", "Ahmet")
        ),
    )
}



























