package com.samar.hitmovies.data.repository

import com.samar.hitmovies.data.remote.MovieApi
import com.samar.hitmovies.data.remote.dto.GenreResponse
import com.samar.hitmovies.domain.repository.MovieRepository
import java.util.*

class MoviesRepositoryImp(private val movieApi: MovieApi): MovieRepository {

    override suspend fun getGenres(): GenreResponse {
        return movieApi.getGenres()
    }

}