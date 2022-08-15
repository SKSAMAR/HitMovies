package com.samar.hitmovies.domain.repository

import com.samar.hitmovies.data.remote.dto.GenreResponse

interface MovieRepository {
    suspend fun getGenres(): GenreResponse
}