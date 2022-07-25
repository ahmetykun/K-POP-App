package com.ahmet.ahmetapi_deneme_1.data.paging_source

import androidx.paging.*
import androidx.paging.RemoteMediator.*
import androidx.test.core.app.ApplicationProvider
import com.ahmet.ahmetapi_deneme_1.data.local.AhmetDatabase
import com.ahmet.ahmetapi_deneme_1.data.remote.FakeAhmetApi2
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HeroRemoteMediatorTest {

    private lateinit var ahmetApi:FakeAhmetApi2
    private lateinit var ahmetDatabase: AhmetDatabase

    @Before
    fun setup(){
        ahmetApi = FakeAhmetApi2()
        ahmetDatabase = AhmetDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }

    @After
    fun cleanup(){
        ahmetDatabase.clearAllTables()
    }

    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runBlocking {
            val remoteMediator =HeroRemoteMediator(
                ahmetApi = ahmetApi,
                ahmetDatabase = ahmetDatabase
            )
            val pagingState =PagingState<Int, Hero>(
               pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result =remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertFalse((result as MediatorResult.Success).endOfPaginationReached)

    }
    @ExperimentalPagingApi
    @Test
    fun refreshLoadSuccesAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            ahmetApi.clearData()
            val remoteMediator =HeroRemoteMediator(
                ahmetApi = ahmetApi,
                ahmetDatabase = ahmetDatabase
            )
            val pagingState =PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result =remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Success)
            assertTrue((result as MediatorResult.Success).endOfPaginationReached)
        }
    @ExperimentalPagingApi
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            ahmetApi.addException()
            val remoteMediator =HeroRemoteMediator(
                ahmetApi = ahmetApi,
                ahmetDatabase = ahmetDatabase
            )
            val pagingState =PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0
            )
            val result =remoteMediator.load(LoadType.REFRESH, pagingState)
            assertTrue(result is MediatorResult.Error)

        }

}















