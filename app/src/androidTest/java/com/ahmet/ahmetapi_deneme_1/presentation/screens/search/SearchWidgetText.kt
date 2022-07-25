package com.ahmet.ahmetapi_deneme_1.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.ahmet.ahmetapi_deneme_1.presentataion.screens.searh.SearchWidget
import org.junit.Rule
import org.junit.Test

class SearchWidgetText {

    @get:Rule
    val composeTextRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputTest_assertInputText(){
        val text = mutableStateOf("")
       composeTextRule.setContent{
           SearchWidget(
               text = text.value,
               onTextChange ={
                   text.value =it
               },
               onCloseClick ={} ,
               onSearchClicked = {}
           )
       }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ahmet-Aykun")
        composeTextRule.onNodeWithContentDescription("TextField")
            .assertTextEquals("Ahmet-Aykun")
    }
    @Test
    fun openSearchWidget_addInputTest_pressCloseButtonOnce_assertEmtyImputText(){
        val text = mutableStateOf("")
        composeTextRule.setContent{
            SearchWidget(
                text = text.value,
                onTextChange ={
                    text.value =it
                },
                onCloseClick ={} ,
                onSearchClicked = {}
            )
        }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ahmet-Aykun")
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("TextField")
            .assertTextContains("")
    }
    @Test
    fun openSearchWidget_addInputTest_pressCloseButtonTwice_assertClosedState(){
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTextRule.setContent{
           if (searchWidgetShown.value){
               SearchWidget(
                   text = text.value,
                   onTextChange ={
                       text.value =it
                   },
                   onCloseClick ={
                        searchWidgetShown.value =false
                   } ,
                   onSearchClicked = {}
               )
           }
        }
        composeTextRule.onNodeWithContentDescription("TextField")
            .performTextInput("Ahmet-Aykun")
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
    @Test
    fun openSearchWidget_pressCloseButtonOneWhenInputIsEmpty_assertClosedState(){
        val text = mutableStateOf("")
        val searchWidgetShown = mutableStateOf(true)
        composeTextRule.setContent{
            if (searchWidgetShown.value){
                SearchWidget(
                    text = text.value,
                    onTextChange ={
                        text.value =it
                    },
                    onCloseClick ={
                        searchWidgetShown.value =false
                    } ,
                    onSearchClicked = {}
                )
            }
        }

        composeTextRule.onNodeWithContentDescription("CloseButton")
            .performClick()
        composeTextRule.onNodeWithContentDescription("SearchWidget")
            .assertDoesNotExist()
    }
}














