package com.samar.hitmovies.util

sealed class ScreenNav(val route: String){
    object LIVE: ScreenNav("live")
    object FAVOURITE: ScreenNav("favourite")
}
