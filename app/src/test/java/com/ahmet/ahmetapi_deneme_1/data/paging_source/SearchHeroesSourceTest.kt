

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadResult.*
import androidx.paging.PagingSource.LoadParams
import com.ahmet.ahmetapi_deneme_1.data.paging_source.SearchHeroesSource
import com.ahmet.ahmetapi_deneme_1.data.remote.AhmetApi
import com.ahmet.ahmetapi_deneme_1.data.remote.FakeAhmetApi
import com.ahmet.ahmetapi_deneme_1.domain.model.Hero
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
class SearchHeroesSourceTest {
    private lateinit var ahmetApi:AhmetApi
    private lateinit var heroes: List<Hero>

    @Before
    fun stup(){
        ahmetApi = FakeAhmetApi()
        heroes = listOf(
                Hero(
                    id = 1,
                    name = "Sasuke",
                    image = "",
                    about = "",
                    rating = 5.0,
                    power = 0,
                    month = "",
                    day = "",
                    family = listOf(),
                    abilities = listOf(),
                    natureTypes = listOf()
                ),
        Hero(
            id = 2,
            name = "Naruto",
            image = "",
            about = "",
            rating = 5.0,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        ),
        Hero(
            id = 3,
            name = "Sakura",
            image = "",
            about = "",
            rating = 4.5,
            power = 0,
            month = "",
            day = "",
            family = listOf(),
            abilities = listOf(),
            natureTypes = listOf()
        )
        )
    }


    @Test
    fun `Search api with existing hero name ,expect single hero result, assert LoadResult_Page` ()=
        runTest {
            val heroesSource = SearchHeroesSource(ahmetApi = ahmetApi, query = "Sasuke")
            assertEquals<PagingSource.LoadResult<Int,Hero >>(
                expected = Page(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    @Test
    fun `Search api with existing hero name ,expect multiple hero result, assert LoadResult_Page` ()=
        runTest {
            val heroesSource = SearchHeroesSource(ahmetApi = ahmetApi, query = "Sa")
            assertEquals<PagingSource.LoadResult<Int,Hero >>(
                expected = Page(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                actual = heroesSource.load(
                    PagingSource.LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
        }
    @Test
    fun `Search api with empty hero name , assert empty heroes list and LoadResult_Page` ()=
        runTest {
            val heroesSource = SearchHeroesSource(ahmetApi = ahmetApi, query = "")
             val loadResult =heroesSource.load(

                 PagingSource.LoadParams.Refresh(
                     key = null,
                     loadSize = 3,
                     placeholdersEnabled = false
                 )
             )
            val result =ahmetApi.searchHeroes("").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
    @Test
    fun `Search api with non_existing hero name , assert empty heroes list and LoadResult_Page` ()=
        runTest {
            val heroesSource = SearchHeroesSource(ahmetApi = ahmetApi, query = "Unknown")
            val loadResult =heroesSource.load(

                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )
            val result =ahmetApi.searchHeroes("Unknown").heroes

            assertTrue { result.isEmpty() }
            assertTrue { loadResult is PagingSource.LoadResult.Page }
        }
}









































