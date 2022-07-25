package com.ahmet.ahmetapi_deneme_1.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.data.repository.LocalDataSourceImp
import com.ahmet.ahmetapi_deneme_1.domain.repository.LocalDataSource
import com.ahmet.ahmetapi_deneme_1.util.Constants.AHMET_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModel {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AhmetDatabase{
        return Room.databaseBuilder(
        context,
        AhmetDatabase::class.java,
        AHMET_DATABASE
    ).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: AhmetDatabase
    ):LocalDataSource{
        return LocalDataSourceImp(
            ahmetDatabase = database
        )
    }

}