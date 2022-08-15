package com.samar.hitmovies.data.repository

import com.samar.hitmovies.data.remote.MovieApi
import com.samar.hitmovies.domain.repository.MovieRepository

class MoviesRepositoryImp(val movieApi: MovieApi): MovieRepository {

}