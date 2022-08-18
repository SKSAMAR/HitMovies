package com.samar.hitmovies.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun<T> OptionTag(
    hint: String,
    tag: MutableState<T>,
    onClick: ()->Unit
) {
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(14.dp)
            ).clickable { onClick.invoke() }
            .padding(vertical = 5.dp, horizontal = 20.dp)
    ) {
        Text(
            text = hint,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.overline
        )
        Text(
            text = if(tag.value.toString() == "0")"All" else tag.value.toString(),
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }

}