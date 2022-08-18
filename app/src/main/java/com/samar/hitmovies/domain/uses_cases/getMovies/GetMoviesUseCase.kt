package com.samar.hitmovies.domain.uses_cases.getMovies

import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieFetchResponse
import com.samar.hitmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class GetMoviesUseCase
@Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(page: Int, genre: String, year: Int, type: String): Flow<Resource<MovieFetchResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getTitles(page = page, genre = genre, titleType = type.toLowerCase(), year = year)
            if(!movies.message.isNullOrEmpty()){
                emit(Resource.Error(movies.message!!))
            }
            else if(movies.results.isNotEmpty()){
                emit(Resource.Success(movies))
            }
            else{
                emit(Resource.Error("Not Found"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

    }


    fun getMoviesByTitle(
        movieName: String,
        info: String = "mini_info",
        limit: Int = 30,
        page: Int = 1,
        titleType: String
    ): Flow<Resource<MovieFetchResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getMoviesByTitle(movieName, info, limit, page, titleType)
            if(!movies.message.isNullOrEmpty()){
                emit(Resource.Error(movies.message!!))
            }
            else if(movies.results.isNotEmpty()){
                emit(Resource.Success(movies))
            }
            else{
                emit(Resource.Error("Not Found"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

    }

    fun getNextMovie(
        next: String,
        updateNext: (String?)->Unit
    ): Flow<Resource<MovieFetchResponse>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getNext(next)
            updateNext.invoke(movies.next)
            if(!movies.message.isNullOrEmpty()){
                emit(Resource.Error(movies.message!!))
            }
            else if(movies.results.isNotEmpty()){
                emit(Resource.Success(movies))
            }
            else{
                emit(Resource.Error("Not Found"))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

    }
}