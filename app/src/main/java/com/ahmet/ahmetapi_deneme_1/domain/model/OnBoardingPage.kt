package com.ahmet.ahmetapi_deneme_1.domain.model

import androidx.annotation.DrawableRes
import com.ahmet.ahmetapi_deneme_1.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String,

){
    object First: OnBoardingPage(
        image = R.drawable.birinci,
        title = "Greetings",
        description = "K-POP hayranı mısınız? Eğer öyleyseniz, sizin için doğru yer. "
    )
    object Second: OnBoardingPage(
        image = R.drawable.ikinci,
        title = "Explore",
        description = "Favori K-pop grupun hangisi?                   K-pop hakkında bilgi edinmek için buradasınız. "
    )
    object Trirh: OnBoardingPage(
        image = R.drawable.ucuncu,
        title = "Power",
        description = "K-pop dinlemekten hoşlanıyormusunuz?    O halde başlayalım. "
    )

}

