package com.samar.hitmovies.presentation.movies

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BasicAnimation
import com.samar.hitmovies.presentation.common.OptionTag
import com.samar.hitmovies.presentation.movies.component.MoviesCardDesign
import com.samar.hitmovies.util.ConnectionLiveData
import com.samar.hitmovies.util.CustomSearchViewBasic
import com.samar.hitmovies.util.ScreenNav
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@Composable
fun MoviesScreen(viewModel: MoviesViewModel, navController: NavController) {
    val configuration = LocalConfiguration.current
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

                when(configuration.orientation) {
                    Configuration.ORIENTATION_PORTRAIT -> {
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp),
                            mainAxisSpacing = 5.dp,
                            crossAxisSpacing = 5.dp,
                        ) {
                            OptionTag(hint = "Year", tag = viewModel.year) { isYearExpanded.value = true }
                            OptionTag(hint = "Genre", tag = viewModel.genre) {
                                isGenreExpanded.value = true
                            }
                            OptionTag(hint = "Type", tag = viewModel.type) { isTypeExpanded.value = true }

                            IconButton(
                                onClick = {
                                    navController.navigate(ScreenNav.FAVOURITE.route)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Checklist,
                                    contentDescription = "",
                                    tint = MaterialTheme.colors.primary
                                )
                            }
                        }
                    }
                    else->{

                    }
                }

            }
        }) {

        val isNetworkAvailable = ConnectionLiveData(LocalContext.current).observeAsState(initial = false)
        AnimatedVisibility(
            visible = !isNetworkAvailable.value,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.05f)
        ) {
            Card(
                backgroundColor = Color.White,
                elevation = 3.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "No Internet Available",
                        color = Color.Red,
                        fontSize = 12.sp
                    )
                }
            }
        }
        if (isNetworkAvailable.value){
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
                    DropDownContainer(
                        viewModel = viewModel,
                        isGenreExpanded = isGenreExpanded,
                        isTypeExpanded = isTypeExpanded,
                        isYearExpanded = isYearExpanded
                    )
                    MoviesContainer(viewModel, configuration)
                }
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
fun MoviesContainer(viewModel: MoviesViewModel, configuration: Configuration) {

    LaunchedEffect(key1 = viewModel.movieTitle.value) {
        viewModel.getMoviesByName()
        viewModel.getYear()
        viewModel.getGenreList()
        viewModel.getTypeList()
    }
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
            val gridState = rememberLazyGridState()

            when(configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        state = gridState,
                        verticalArrangement = Arrangement.Center
                    ) {
                        items(movieList) {movie->
                            MoviesCardDesign(movie = movie, action = { viewModel.addToFavourite(movie) })
                        }
                    }
                    if (gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == gridState.layoutInfo.totalItemsCount - 1) {
                        viewModel.getNext()
                    }
                }
                else -> {
                    val columnState = rememberLazyListState()
                    LazyColumn(
                        state = columnState,
                    ) {
                        items(movieList) {
                            MoviesCardDesign(movie = it, action = { viewModel.addToFavourite(it) })
                        }
                    }
                    /**
                    LazyVerticalGrid(
                    columns = GridCells.Fixed(gridCount),
                    state = gridState
                    ) {
                    items(movieList.size - 1) {
                    if(movieList[it].titleText.text.contains(viewModel.movieTitle.value, true)){
                    MoviesCardDesign(movie = movieList[it], action = { viewModel.deleteFromFavourite(movieList[it]) }, isDislike = true)
                    }
                    }
                    }
                    **/

                    if (columnState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == columnState.layoutInfo.totalItemsCount - 1) {
                        viewModel.getNext()
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