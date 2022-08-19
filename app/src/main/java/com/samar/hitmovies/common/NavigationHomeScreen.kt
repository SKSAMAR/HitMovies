package com.samar.hitmovies.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samar.hitmovies.presentation.favoutites.FavouriteScreen
import com.samar.hitmovies.presentation.movies.MoviesScreen
import com.samar.hitmovies.presentation.movies.MoviesViewModel
import com.samar.hitmovies.util.ConnectionLiveData
import com.samar.hitmovies.util.ScreenNav


@Composable
fun NavigationHomeScreen(moviesViewModel: MoviesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNav.LIVE.route){
        composable( route = ScreenNav.LIVE.route){
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
                MoviesScreen(viewModel = moviesViewModel, navController = navController)
            }
        }
        composable( route = ScreenNav.FAVOURITE.route ){  FavouriteScreen( )    }
    }
}