package com.samar.hitmovies.presentation.favoutites

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.domain.model.MovieDetail
import com.samar.hitmovies.domain.model.toMovieDetailDto
import com.samar.hitmovies.presentation.movies.component.MoviesCardDesign
import com.samar.hitmovies.util.CustomSearchViewBasic
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun FavouriteScreen(viewModel: FavouriteViewModel = hiltViewModel()) {
    val configuration = LocalConfiguration.current
    val toolbarState = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = toolbarState,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {

        }) {
        Column {
            when(configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    Card(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                        elevation = 16.dp,
                        backgroundColor = Color.White,
                        shape = RoundedCornerShape(10.dp),
//                        border = BorderStroke(width = 0.9.dp, color = Color.Gray)
                    ) {
                        CustomSearchViewBasic(query = viewModel.movieTitle)
                    }
                }
                else->{

                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MoviesContainer(viewModel)
            }
        }
    }

}

@Composable
fun MoviesContainer(viewModel: FavouriteViewModel) {

    val state = viewModel.state.value
    val movieState = state.receivedResponse?.observeAsState()
    val movieList = movieState?.value?.map {  it.toMovieDetailDto()  }

    val configuration = LocalConfiguration.current
    var gridCount by remember {
        mutableStateOf(2)
    }
    gridCount = when(configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            4
        }
        else -> {
            2
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.error.isNotEmpty()) {
            if(state.error == "Not Found"){
                BasicAnimation(
                    modifier = Modifier
                        .fillMaxSize(.5f)
                        .align(Alignment.TopCenter),
                    animation = R.raw.not_found
                )
            }
            else{
                Text(
                    text = state.error,
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }

        movieList?.let {

            val gridState = rememberLazyGridState()
            when(configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        state = gridState,
                        verticalArrangement = Arrangement.Center
                    ) {
                        items(movieList) {movie->
                            if(movie.titleText.text.contains(viewModel.movieTitle.value, true)){
                                MoviesCardDesign(movie = movie, action = { viewModel.deleteFromFavourite(movie) }, isDislike = true)
                            }
                        }
                    }
                }
                else -> {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(gridCount),
                        state = gridState
                    ) {
                        items(movieList) {movie->
                            if(movie.titleText.text.contains(viewModel.movieTitle.value, true)){
                                MoviesCardDesign(movie = movie, action = { viewModel.deleteFromFavourite(movie) }, isDislike = true)
                            }
                        }
                    }
                }
            }
        }
        if (state.isLoading) {
            BasicAnimation(
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .align(Alignment.TopCenter),
            )
        }

        viewModel.animation.value?.let {anim->
            BasicAnimation(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .align(Alignment.Center),
                animation = anim
            )
        }
    }
}