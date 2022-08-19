package com.samar.hitmovies.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.samar.hitmovies.common.NavigationHomeScreen
import com.samar.hitmovies.domain.model.ScreenState
import com.samar.hitmovies.presentation.movies.MoviesScreen
import com.samar.hitmovies.presentation.movies.MoviesViewModel
import com.samar.hitmovies.ui.theme.HitMoviesTheme
import com.samar.hitmovies.util.ConnectionLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val movieViewModel by viewModels<MoviesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HitMoviesTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationHomeScreen(moviesViewModel = movieViewModel)
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HitMoviesTheme {
//        MoviesScreen(moviesViewModel = movieViewModel)
    }
}