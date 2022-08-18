package com.samar.hitmovies.presentation.genres

/**
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.samar.hitmovies.common.BaseScaffold
import com.samar.hitmovies.common.BasicAnimation

@Composable
fun GenreScreen(viewModel: GenreViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    BaseScaffold(title = "Genres") {

        if (state.isLoading) {
            BasicAnimation(
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .align(Alignment.Center)
            )
        }
        if (state.error.isNotEmpty()) {
            Text(
                text = state.error,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.subtitle1
            )
        }
        state.receivedResponse?.let {
            /**
            LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                items(state.receivedResponse) {
                    GenresCardDesign(genre = it.toGenre(), clickable = {

                    })
                }
            }
            **/
        }
    }
}

**/