package com.samar.hitmovies.common

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samar.hitmovies.presentation.favoutites.FavouriteScreen
import com.samar.hitmovies.presentation.movies.MoviesScreen
import com.samar.hitmovies.presentation.movies.MoviesViewModel
import com.samar.hitmovies.util.ScreenNav


@Composable
fun NavigationHomeScreen(moviesViewModel: MoviesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNav.LIVE.route){
        composable( route = ScreenNav.LIVE.route){
            MoviesScreen(viewModel = moviesViewModel, navController = navController)
        }
        composable( route = ScreenNav.FAVOURITE.route ){  FavouriteScreen()    }
    }
}