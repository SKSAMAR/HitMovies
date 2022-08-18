package com.samar.hitmovies.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.samar.hitmovies.common.Constants
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.presentation.detail.DetailScreen
import com.samar.hitmovies.presentation.detail.DetailViewModel

class MoviesDetailActivity: ComponentActivity() {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeData()
    }



    private fun initializeData(){
        if (viewModel.movieDetailDto==null) {
            viewModel.movieDetailDto = intent.getSerializableExtra(Constants.MovieData) as MovieDetailDto
            Log.d("MovieDetailData", viewModel.movieDetailDto.toString())
        }
        setContent {
            DetailScreen(viewModel = viewModel)
        }
    }

}