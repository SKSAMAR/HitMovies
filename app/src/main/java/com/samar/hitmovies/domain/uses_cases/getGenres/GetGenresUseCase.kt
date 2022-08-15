package com.samar.hitmovies.domain.uses_cases.getGenres

import androidx.compose.ui.semantics.SemanticsProperties.Error
import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.*
import javax.inject.Inject

class GetGenresUseCase
@Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(): Flow<Resource<Objects>> = flow {
        try {
            emit(Resource.Loading())
            val genres = repository.getGenres()
            emit(Resource.Success(genres))

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