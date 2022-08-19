package com.samar.hitmovies.presentation.favoutites

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.samar.hitmovies.R
import com.samar.hitmovies.common.BaseViewModel
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.domain.model.MovieDetail
import com.samar.hitmovies.domain.model.ScreenState
import com.samar.hitmovies.domain.uses_cases.getMovies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel
@Inject constructor( private val getMoviesUseCase: GetMoviesUseCase): BaseViewModel<LiveData<List<MovieDetail>>>() {

    val movieTitle = mutableStateOf("")
    val animation: MutableState<Int?> = mutableStateOf(null)

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies(){
        _state.value = ScreenState(receivedResponse = getMoviesUseCase.getFavouriteMovies())
    }

    fun deleteFromFavourite(movieDetailDto: MovieDetailDto){
        viewModelScope.launch(Dispatchers.IO) {
            val result = getMoviesUseCase.deleteFromFavourite(movieDetailDto)
            dislikedAnimation()
        }
    }


    private fun dislikedAnimation(){
        viewModelScope.launch(Dispatchers.IO){
            animation.value = R.raw.delete
            delay(4000L)
            animation.value = null
        }
    }
}