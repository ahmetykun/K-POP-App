package com.ahmet.ahmetapi_deneme_1.di

import androidx.paging.ExperimentalPagingApi
import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.data.remote.AhmetApi
import com.ahmet.ahmetapi_deneme_1.data.repository.RemoteDataSourceImp
import com.ahmet.ahmetapi_deneme_1.domain.repository.RemoteDataSource
import com.ahmet.ahmetapi_deneme_1.util.Constants.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalPagingApi
@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
    return OkHttpClient.Builder()
        .readTimeout(15, TimeUnit.SECONDS)
        .connectTimeout(15,TimeUnit.SECONDS)
        .build()
    }

  @Provides
  @Singleton
 fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit{
    val contentType ="application/json".toMediaType()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
   }

    @Provides
    @Singleton
    fun provideAhmetApi(retrofit: Retrofit): AhmetApi{
      return  retrofit.create(AhmetApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        ahmetApi: AhmetApi,
        ahmetDatabase: AhmetDatabase
    ): RemoteDataSource{
        return RemoteDataSourceImp(
            ahmetApi = ahmetApi,
            ahmetDatabase = ahmetDatabase
        )
    }

}
