package com.samar.hitmovies.data.remote

import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.*

interface MovieApi {

    @GET("api/genres")
    suspend fun getGenres(): Objects


//    @GET("/titles")
//    suspend fun getTitles(
//        @Query("info") info: String = "mini_info",
//        @Query("limit") limit: Int = 30,
//        @Query("page") page: Int = 1,
//        @Query("titleType") titleType: String = "movie",
//        @Query("genre") genre: String,
//        @Query("year") year: Int
//    ): MovieFetchResponse


    @GET
    suspend fun getTitles(
        @Url url: String
    ): MovieFetchResponse

    @GET
    suspend fun getNext(@Url next: String): MovieFetchResponse


    @GET
    suspend fun getMoviesByTitle(@Url url: String): MovieFetchResponse

}