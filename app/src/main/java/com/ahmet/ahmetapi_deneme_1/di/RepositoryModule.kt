package com.ahmet.ahmetapi_deneme_1.di

import android.content.Context
import com.ahmet.ahmetapi_deneme_1.data.repository.DataStoreOperationsImpl
import com.ahmet.ahmetapi_deneme_1.data.repository.Repository
import com.ahmet.ahmetapi_deneme_1.domain.repository.DataStoreOparations
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.UseCases
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.save_onboarding.SevOnBoardingUseCase
import com.ahmet.ahmetapi_deneme_1.domain.use_cases.search_heroes.SearchHeroesUseCse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOparations{
        return DataStoreOperationsImpl(context = context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases{
        return UseCases(
            seveOnBoardingCase = SevOnBoardingUseCase(repository),
            readOnBoardingCase = ReadOnBoardingUseCase(repository),
            getAllHeroesUseCase = GetAllHeroesUseCase(repository),
            searchHeroesUseCase = SearchHeroesUseCse(repository),
            getSelectedHeroUseCase = GetSelectedHeroUseCase(repository)
        )
    }
}