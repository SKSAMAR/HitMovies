package com.samar.hitmovies.presentation.genres

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.samar.hitmovies.common.BaseScaffold

@Composable
fun GenreScreen(viewModel: GenreViewModel = hiltViewModel()){
    val state = viewModel.state.value
    BaseScaffold {
        if(state.isLoading) {
            CircularProgressIndicator()
        }
        if(state.error.isNotEmpty()){
            Text(text = state.error, modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.h4)
        }
        state.receivedResponse?.let {
            Text(text = "Response: "+state.receivedResponse.toString(), modifier = Modifier.align(Alignment.Center), style = MaterialTheme.typography.h4)
        }
    }
}