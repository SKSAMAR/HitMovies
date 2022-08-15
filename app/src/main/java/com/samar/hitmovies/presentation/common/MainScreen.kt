package com.samar.hitmovies.presentation.common

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samar.hitmovies.presentation.genres.GenreScreen
import com.samar.hitmovies.util.ScreenNav

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenNav.GENRE.route){
        composable(ScreenNav.GENRE.route){     GenreScreen()     }
        composable(ScreenNav.YEARS.route){          }
        composable(ScreenNav.TRAILERS.route){       }
    }
}