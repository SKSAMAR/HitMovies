package com.samar.hitmovies.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samar.hitmovies.ui.theme.LightestGrey

@Composable
fun CustomSearchView(
    query: MutableState<String>,
){
    val textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
    Card(
        elevation = 4.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {

            TextField(
                modifier = Modifier.fillMaxWidth(.9f),
                value =query.value ,
                onValueChange = { query.value = it },
                placeholder = {
                    Text(text = "Search")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search")
                },
                colors = textFieldColors,
                maxLines = 1
            )

        }
    }
}

@Composable
fun CustomSearchViewBasic(
    query: MutableState<String>,
){

    CustomTextField(
        text = query,
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                null,
                tint = LocalContentColor.current.copy(alpha = 0.4f),
                modifier = Modifier.padding(end = 20.dp)
            )
        },
        trailingIcon = null,
        modifier = Modifier
            .background(
                MaterialTheme.colors.surface,
                RoundedCornerShape(percent = 0)
            )
            .padding(vertical = 4.dp, horizontal = 18.dp)
            .height(40.dp),
        fontSize = 14.sp,
        placeholderText = "Search"
    )
}


@Composable
fun BasicCard(
    modifier: Modifier = Modifier,
    corner: Dp,
    content: @Composable () -> Unit
){

    Card(
        modifier = modifier,
        border = BorderStroke(width = 0.2.dp, color = LightestGrey),
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner),
        content = content
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    text: MutableState<String>,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.body2.fontSize
) {
    val (focusRequester) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        value = text.value,
        onValueChange = {text.value = it},
        modifier = Modifier.fillMaxWidth()
            .background(
                MaterialTheme.colors.surface,
                MaterialTheme.shapes.small,
            ).focusRequester(focusRequester),
        singleLine = true,
        decorationBox = {innerTextField->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ){
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.value.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { keyboardController?.hide() }
        )
    )
}