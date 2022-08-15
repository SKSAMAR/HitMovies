package com.samar.hitmovies.presentation.genres

import androidx.lifecycle.viewModelScope
import com.samar.hitmovies.common.BaseViewModel
import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.data.remote.dto.GenreDto
import com.samar.hitmovies.domain.model.ScreenState
import com.samar.hitmovies.domain.uses_cases.getGenres.GetGenresUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject

@HiltViewModel
class GenreViewModel
@Inject constructor( private val getGenresUseCase: GetGenresUseCase): BaseViewModel<List<GenreDto>>() {
    init {
        getGenres()
    }

    private fun getGenres(){
        getGenresUseCase.invoke().onEach {
            when(it){
                is Resource.Success->{
                    _state.value = ScreenState(receivedResponse = it.data)
                }
                is Resource.Error->{
                    _state.value = ScreenState(error = it.message?:"Some Error")
                }
                is Resource.Loading->{
                    _state.value = ScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}