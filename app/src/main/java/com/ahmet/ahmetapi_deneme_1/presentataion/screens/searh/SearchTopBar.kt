package com.ahmet.ahmetapi_deneme_1.presentataion.screens.searh


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.ahmet.ahmetapi_deneme_1.R
import com.ahmet.ahmetapi_deneme_1.ui.theme.TOP_APP_BAR_HIGHT
import com.ahmet.ahmetapi_deneme_1.ui.theme.topAppARAMAbar
import com.ahmet.ahmetapi_deneme_1.ui.theme.topAppARAMAbarbacgraunt

@Composable
fun SearchTopBar(
    text:String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClick: () -> Unit

) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClick = onCloseClick
    )
}
@Composable
fun SearchWidget(
    text:String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClick: () -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HIGHT)
            .semantics {
                       contentDescription= "SearchWidget"
            },
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppARAMAbarbacgraunt
    ) {
        TextField(
            modifier =Modifier
                .fillMaxWidth()
                .semantics {
                    contentDescription= "TextField"
                },
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = {

                Text(modifier =Modifier
                    .alpha(alpha = ContentAlpha.medium),
                    text = "Ara...",
                color = Color.White
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppARAMAbar
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.
                    alpha(alpha = ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_icon),
                        tint = MaterialTheme.colors.topAppARAMAbar
                    )
                }
            },
        trailingIcon = {
            IconButton(
                modifier = Modifier
                    .semantics {
                        contentDescription= "CloseButton"
                    },
                onClick = {
                    if (text.isNotEmpty()){
                        onTextChange("")
                    }else{
                        onCloseClick()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_icon),
                    tint = MaterialTheme.colors.topAppARAMAbar
                )
            }
       },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),

            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topAppARAMAbar
            )
        )
    }
}


@Composable
@Preview
fun SearchWidgetPreview(){
        SearchWidget(
            text = "",
            onTextChange = {},
            onSearchClicked = {},
            onCloseClick = {}
        )
}



