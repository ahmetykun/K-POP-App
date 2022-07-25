package com.ahmet.ahmetapi_deneme_1.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.ahmet.ahmetapi_deneme_1.presentataion.component.RatingStarWidget
import com.ahmet.ahmetapi_deneme_1.ui.theme.SMALL_PADDING
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    var composeTestRule = createComposeRule()

@Test
fun passZeroPointZeroValue_Assert_FiveEmptyStars(){
    composeTestRule.setContent {
        RatingStarWidget(
           modifier = Modifier.padding(all = SMALL_PADDING),
            rating = 0.0

             )
         }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }

    @Test
    fun passZeroPointFiveValue_Assert_FourEmptyStars_and_OneHalfFilledStar(){
        composeTestRule.setContent {
            RatingStarWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.5

            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }

    @Test
    fun passZeroPointSixValue_Assert_FourEmptyStars_and_OneFilledStar(){
        composeTestRule.setContent {
            RatingStarWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.6

            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(4)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(1)
    }
    @Test
    fun passFourPointZeroValue_Assert_FourFilledStars_and_OneEmptyStar(){
        composeTestRule.setContent {
            RatingStarWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 4.0

            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(1)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(4)
    }
    @Test
    fun Negative_passFourPointZeroValue_Assert_FourFilledStars_and_OneEmptyStar(){
        composeTestRule.setContent {
            RatingStarWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = -4.0

            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
    }
    @Test
    fun Invaled_passFourPointZeroValue_Assert_FiveEmptyStar(){
        composeTestRule.setContent {
            RatingStarWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.1

            )
        }
        composeTestRule.onAllNodesWithContentDescription("EmptyStar")
            .assertCountEquals(5)
        composeTestRule.onAllNodesWithContentDescription("HalfFilledStar")
            .assertCountEquals(0)
        composeTestRule.onAllNodesWithContentDescription("FilledStar")
            .assertCountEquals(0)
}}






















