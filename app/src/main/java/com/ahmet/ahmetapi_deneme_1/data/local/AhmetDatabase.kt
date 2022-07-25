package com.ahmet.ahmetapi_deneme_1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmet.ahmetapi_deneme_1.data.local.dao.HeroDao
import com.ahmet.ahmetapi_deneme_1.data.local.dao.HeroRemoteKeyDao
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import com.ahmet.ahmetapi_deneme_1.domain.model.HeroRemoteKeys
import dagger.Provides


@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1  )
@TypeConverters(DatabaseConverter::class)

abstract class AhmetDatabase: RoomDatabase()
{
    companion object {
        fun create(context: Context, useInMemory: Boolean): AhmetDatabase{
            val databaseBuilder= if (useInMemory){
                Room.inMemoryDatabaseBuilder(context, AhmetDatabase::class.java)
            }else{
                Room.databaseBuilder(context, AhmetDatabase::class.java,"test_database.dp")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    
abstract fun heroDao(): HeroDao
abstract fun heroRemoteKeysDao(): HeroRemoteKeyDao


}