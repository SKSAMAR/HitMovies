package com.samar.hitmovies.domain.repository

import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import java.util.*

interface MovieRepository {
    suspend fun getGenres(): Objects
    suspend fun getNext(next: String): MovieFetchResponse
    suspend fun getTitles(
        info: String = "mini_info",
        limit: Int = 30,
        page: Int = 1,
        titleType: String,
        genre: String,
        year: Int
    ): MovieFetchResponse
    suspend fun getMoviesByTitle(
        movieName: String,
        info: String = "mini_info",
        limit: Int = 30,
        page: Int = 1,
        titleType: String
    ): MovieFetchResponse
}