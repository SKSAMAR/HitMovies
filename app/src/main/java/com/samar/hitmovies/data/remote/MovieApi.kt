package com.samar.hitmovies.data.remote

import retrofit2.http.GET
import java.util.*

interface MovieApi {

    @GET("api/genres")
    suspend fun getGenres():Objects
}