package com.samar.hitmovies.data.repository

import com.samar.hitmovies.data.remote.MovieApi
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import com.samar.hitmovies.domain.repository.MovieRepository
import java.util.*

class MoviesRepositoryImp(private val movieApi: MovieApi): MovieRepository {

    override suspend fun getGenres(): Objects {
        return movieApi.getGenres()
    }

    override suspend fun getNext(next: String): MovieFetchResponse {
        return movieApi.getNext(next)
    }

//    override suspend fun getTitles(
//        info: String,
//        limit: Int,
//        page: Int,
//        titleType: String,
//        genre: String,
//        year: Int
//    ): MovieFetchResponse {
//        return movieApi.getTitles(info, limit, page, titleType, genre, year)
//    }

    override suspend fun getTitles(
        url: String,
    ): MovieFetchResponse {
        return movieApi.getTitles(url)
    }

    override suspend fun getMoviesByTitle(
        url: String
    ):  MovieFetchResponse{
        return movieApi.getMoviesByTitle(
        url
        )
    }

}