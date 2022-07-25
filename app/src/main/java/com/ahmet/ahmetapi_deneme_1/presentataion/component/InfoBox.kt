package com.ahmet.ahmetapi_deneme_1.presentataion.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.ui.theme.INFO_ICON_SIZE
import com.ahmet.ahmetapi_deneme_1.ui.theme.SMALL_PADDING
import com.ahmet.ahmetapi_deneme_1.ui.theme.deneme_SIZE
import com.ahmet.ahmetapi_deneme_1.ui.theme.titleColor


@Composable
fun InfoBox(
    icon: Painter,
    iconColor: Color,
    bigText: String,
    smallText: String,
    textColor: Color

){
    Row(verticalAlignment =Alignment.CenterVertically ) {
        Icon(
            modifier = Modifier
                .padding(end = SMALL_PADDING)
                .size(INFO_ICON_SIZE),
            painter = icon,
            contentDescription = stringResource(R.string.info_icon),
            tint = iconColor
        )

        Column {
            Text(
                modifier = Modifier
                    .size(deneme_SIZE),
                text =bigText,
                color = textColor,
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Black
            )
            Text(
                modifier =Modifier
                    .alpha(ContentAlpha.medium),
                text =smallText,
                color = textColor,
                fontSize = MaterialTheme.typography.overline.fontSize

            )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun InfoBoxPreview(){
    InfoBox(
        icon = painterResource(id = R.drawable.ic_simsek),
        iconColor = MaterialTheme.colors.primary,
        bigText ="92",
        smallText ="Power",
        textColor =MaterialTheme.colors.titleColor
    )
}


@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun InfoBoxDARKPreview(){
    InfoBox(
        icon = painterResource(id = R.drawable.ic_simsek),
        iconColor = MaterialTheme.colors.primary,
        bigText ="92",
        smallText ="Power",
        textColor =MaterialTheme.colors.titleColor
    )
}













