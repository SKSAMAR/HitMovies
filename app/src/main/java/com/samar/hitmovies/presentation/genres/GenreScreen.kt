package com.samar.hitmovies.presentation.genres

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.samar.hitmovies.common.BaseScaffold
import com.samar.hitmovies.data.remote.dto.toGenre
import com.samar.hitmovies.presentation.genres.component.GenreDesign
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun GenreScreen(viewModel: GenreViewModel = hiltViewModel()){
    val state = viewModel.state.value
    BaseScaffold(title = "Genres") {

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if(state.error.isNotEmpty()){
            Text(text = state.error, modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.subtitle1)
        }
        state.receivedResponse?.let {
            LazyColumn {
                items(state.receivedResponse) {
                    GenreDesign(genre = it.toGenre(), clickable = {

                    })
                }
            }
        }
    }
}