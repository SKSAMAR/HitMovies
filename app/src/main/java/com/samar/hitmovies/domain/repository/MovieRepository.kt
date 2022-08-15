package com.samar.hitmovies.domain.repository

import java.util.*

interface MovieRepository {
    suspend fun getGenres(): Objects
}