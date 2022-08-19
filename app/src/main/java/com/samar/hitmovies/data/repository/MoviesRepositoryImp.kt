package com.samar.hitmovies.data.repository

import androidx.lifecycle.LiveData
import com.samar.hitmovies.data.db.MovieDatabase
import com.samar.hitmovies.data.remote.MovieApi
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import com.samar.hitmovies.domain.model.MovieDetail
import com.samar.hitmovies.domain.repository.MovieRepository
import java.util.*
import javax.inject.Inject

class MoviesRepositoryImp
@Inject constructor( private val movieApi: MovieApi, private val movieDatabase: MovieDatabase): MovieRepository {

    override suspend fun getGenres(): Objects {
        return movieApi.getGenres()
    }

    override suspend fun getNext(next: String): MovieFetchResponse {
        return movieApi.getNext(next)
    }


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

    override suspend fun addToFavourite(movieDetail: MovieDetail): Long? {
        return movieDatabase.getMovieDao().addToFavourite(movieDetail)
    }

    override suspend fun deleteFromFavourite(movieDetail: MovieDetail): Int? {
        return movieDatabase.getMovieDao().deleteFromFavourite(movieDetail)
    }

    override fun getAllFavourites(): LiveData<List<MovieDetail>> {
        return movieDatabase.getMovieDao().getAllContent()
    }

}