package com.samar.hitmovies.presentation.movies

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.presentation.common.OptionTag
import com.samar.hitmovies.presentation.movies.component.MoviesCardDesign
import com.samar.hitmovies.util.CustomSearchViewBasic
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MoviesScreen(viewModel: MoviesViewModel) {
    val isYearExpanded = remember { mutableStateOf(false) }
    val isGenreExpanded = remember { mutableStateOf(false) }
    val isTypeExpanded = remember { mutableStateOf(false) }
    val toolbarState = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier,
        state = toolbarState,
        scrollStrategy = ScrollStrategy.EnterAlways,
        toolbar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp, top = 9.dp, bottom = 4.dp)
            ) {

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                    mainAxisSpacing = 10.dp,
                    crossAxisSpacing = 10.dp,
                ) {
                    OptionTag(hint = "Year", tag = viewModel.year) { isYearExpanded.value = true }
                    OptionTag(hint = "Genre", tag = viewModel.genre) {
                        isGenreExpanded.value = true
                    }
                    OptionTag(hint = "Type", tag = viewModel.type) { isTypeExpanded.value = true }
                }

            }
        }) {
        Column {
            Card(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                elevation = 6.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 0.9.dp, color = Color.Gray)
            ) {
                CustomSearchViewBasic(query = viewModel.movieTitle)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DropDownContainer(
                    viewModel = viewModel,
                    isGenreExpanded = isGenreExpanded,
                    isTypeExpanded = isTypeExpanded,
                    isYearExpanded = isYearExpanded
                )
                MoviesContainer(viewModel)
            }
        }
    }

}

@Composable
fun DropDownContainer(
    modifier: Modifier = Modifier,
    viewModel: MoviesViewModel,
    isYearExpanded: MutableState<Boolean>,
    isGenreExpanded: MutableState<Boolean>,
    isTypeExpanded: MutableState<Boolean>,
) {
    DropdownMenu(
        expanded = isYearExpanded.value,
        onDismissRequest = { isYearExpanded.value = false },
        modifier = modifier
    ) {
        viewModel.getYear().forEach { label ->
            DropdownMenuItem(onClick = {
                viewModel.year.value = label
                isYearExpanded.value = false
                viewModel.getMovies()
            }) {
                Text(text = if(label==0)"All" else label.toString())
            }
        }
    }

    DropdownMenu(
        expanded = isGenreExpanded.value,
        onDismissRequest = { isGenreExpanded.value = false },
        modifier = modifier
    ) {
        viewModel.getGenreList().forEach { label ->
            DropdownMenuItem(onClick = {
                viewModel.genre.value = label
                isGenreExpanded.value = false
                viewModel.getMovies()
            }) {
                Text(text = label)
            }
        }
    }

    DropdownMenu(
        expanded = isTypeExpanded.value,
        onDismissRequest = { isTypeExpanded.value = false },
        modifier = modifier
    ) {
        viewModel.getTypeList().forEach { label ->
            DropdownMenuItem(onClick = {
                viewModel.type.value = label
                isTypeExpanded.value = false
                viewModel.getMovies()
            }) {
                Text(text = label.displayName)
            }
        }
    }
}

@Composable
fun MoviesContainer(viewModel: MoviesViewModel) {

    LaunchedEffect(key1 = viewModel.movieTitle.value) {
        viewModel.getMoviesByName()
        viewModel.getYear()
        viewModel.getGenreList()
        viewModel.getTypeList()
    }
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
        val state = viewModel.state.value
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
        state.receivedResponse?.let { movieList ->
            val gridState = viewModel.lazyGridState
            LazyVerticalGrid(
                columns = GridCells.Fixed(gridCount),
                state = gridState
            ) {
                items(movieList.size - 1) {
                    MoviesCardDesign(movie = movieList[it])
                }
            }
            if (gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == gridState.layoutInfo.totalItemsCount - 1) {
                viewModel.getNext()
            }

            /**
            clickable = {movie->
            val intent = Intent(context, MoviesDetailActivity::class.java)
            intent.putExtra(Constants.MovieData, movie)
            context.startActivity(intent)
            }
             **/
        }
        if (state.isLoading) {
            BasicAnimation(
                modifier = Modifier
                    .fillMaxSize(.5f)
                    .align(Alignment.TopCenter),
            )
        }
    }
}