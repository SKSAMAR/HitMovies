package com.samar.hitmovies.util

sealed class ScreenNav(val route: String){
    object GENRE: ScreenNav("genre")
    object YEARS: ScreenNav("years")
    object TRAILERS: ScreenNav("trailers")
}
