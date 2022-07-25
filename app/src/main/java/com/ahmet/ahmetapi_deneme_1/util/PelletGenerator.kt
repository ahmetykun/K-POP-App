package com.ahmet.ahmetapi_deneme_1.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PelletGenerator {

    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        content: Context
    ):Bitmap? {
        val loader =ImageLoader(context = content)
        val request = ImageRequest.Builder(context = content)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageRequest = loader.execute(request = request)
        return if (imageRequest is SuccessResult){
            (imageRequest.drawable as BitmapDrawable).bitmap
        }else{
            null
        }
    }

    fun extracColorsFromBitmap(bitmap: Bitmap): Map<String, String>{
        return  mapOf(
            "vibrant" to parseColorSwatch(
                color = Palette.from(bitmap).generate().vibrantSwatch
            ),

             "darkVibrant" to parseColorSwatch(
            color = Palette.from(bitmap).generate().darkVibrantSwatch
          ),
            "onDarkVibrant" to parseBodyColor(
             color =  Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            )
        )
    }
    private fun parseColorSwatch(color: Palette.Swatch?):String {
        return if (color != null){
            val parsedColor = Integer.toHexString(color.rgb)
            return "#$parsedColor"
        }else{
            "#000000"
        }

    }
    private fun parseBodyColor(color: Int?): String {
        return if (color != null){
            val parsedColor = Integer.toHexString(color)
            "#$parsedColor"
        }else{
            "#FFFFFF"
        }
    }
}




















