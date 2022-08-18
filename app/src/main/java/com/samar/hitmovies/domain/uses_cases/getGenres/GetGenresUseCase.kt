package com.samar.hitmovies.domain.uses_cases.getGenres

/**
import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGenresUseCase
@Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(): Flow<Resource<List<GenreDto>>> = flow {
        try {
            emit(Resource.Loading())
            val genres = repository.getGenres()
            if(genres.success){
                emit(Resource.Success(genres.results))
            }
            else{
                emit(Resource.Error(genres.messageStatus))
            }

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server. Check your internet connection."
                )
            )
        }

    }
}

 **/