package com.samar.hitmovies.domain.repository

import androidx.lifecycle.LiveData
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import com.samar.hitmovies.domain.model.MovieDetail
import java.util.*

interface MovieRepository {
    suspend fun getGenres(): Objects
    suspend fun getNext(next: String): MovieFetchResponse

    suspend fun getTitles(
        url: String
    ): MovieFetchResponse

    suspend fun getMoviesByTitle(
        url: String
    ): MovieFetchResponse

    suspend fun addToFavourite(
        movieDetail: MovieDetail
    ): Long?

    suspend fun deleteFromFavourite(
        movieDetail: MovieDetail
    ): Int?

    fun getAllFavourites(): LiveData<List<MovieDetail>>
}