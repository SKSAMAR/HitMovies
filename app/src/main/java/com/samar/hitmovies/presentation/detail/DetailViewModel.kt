package com.samar.hitmovies.presentation.detail

import androidx.lifecycle.ViewModel
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto

class DetailViewModel: ViewModel() {
    var movieDetailDto: MovieDetailDto? = null
}