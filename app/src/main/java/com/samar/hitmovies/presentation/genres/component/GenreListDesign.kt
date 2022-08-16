package com.samar.hitmovies.presentation.genres.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samar.hitmovies.domain.model.Genre

@Composable
fun GenreDesign(genre: Genre, clickable:(Genre)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                clickable.invoke(genre)
            }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = genre.name,
                style = MaterialTheme.typography.body2,
            )
        }
        Divider()
    }
}