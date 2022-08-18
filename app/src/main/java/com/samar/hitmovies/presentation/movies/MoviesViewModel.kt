package com.samar.hitmovies.presentation.movies

import android.util.Log
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.samar.hitmovies.common.BaseViewModel
import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.domain.model.ScreenState
import com.samar.hitmovies.domain.model.TypeModel
import com.samar.hitmovies.domain.uses_cases.getMovies.GetMoviesUseCase
import com.samar.hitmovies.util.Accessable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject constructor( private val getMoviesUseCase: GetMoviesUseCase): BaseViewModel<ArrayList<MovieDetailDto>>() {

    val lazyGridState = LazyGridState()
    val movieTitle = mutableStateOf("")
    var next: String? = null
    var genre = mutableStateOf("Action")
    var type = mutableStateOf(TypeModel("movie", "Movie"))
    var year = mutableStateOf(2022)
    private val yearList = ArrayList<Int>()
    private val typeList = ArrayList<TypeModel>()
    private val genreList = ArrayList<String>()

    init {
        getMovies()
    }
    fun getMovies(page: Int = 1){
        getMoviesUseCase.invoke(page, genre = genre.value, year = year.value, type = type.value.typeCode).onEach {
            when(it){
                is Resource.Success->{
                    it.data?.let {movieFetchResponse->
                        next = it.data.next
                        _state.value = ScreenState(receivedResponse = it.data.results)
                    }
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

    fun getNext(){
        if(Accessable.isAccessable()){
            next?.let {
                Log.d("NEXTIS", next?:"")
                val oldList = _state.value.receivedResponse
                getMoviesUseCase.getNextMovie(it, updateNext = { up-> next = up }).onEach {
                    when(it){
                        is Resource.Success->{
                            it.data?.let {movieFetchResponse->
                                oldList?.let {old->
                                    oldList.addAll(it.data.results)
                                }
                                _state.value = ScreenState(receivedResponse = oldList)
                            }
                        }
                        is Resource.Error->{
                            _state.value = ScreenState(error = it.message?:"Some Error", receivedResponse = oldList)
                        }
                        is Resource.Loading->{
                            _state.value = ScreenState(isLoading = true, receivedResponse = oldList)
                            next = null
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    fun getMoviesByName(
        info: String = "mini_info",
        limit: Int = 30,
        page: Int = 1,
    ){
        if(movieTitle.value.isEmpty()){
            getMovies()
            return
        }
        getMoviesUseCase.getMoviesByTitle(movieTitle.value, info, limit, page, type.value.typeCode).onEach {
            when(it){
                is Resource.Success->{
                    it.data?.let {movieFetchResponse->
                        next = it.data.next
                        _state.value = ScreenState(receivedResponse = it.data.results)
                    }
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

    fun getYear():List<Int>{
        if(yearList.isEmpty()){
            for (i in 1995 until 2023){
                yearList.add(i)
            }
        }
        return yearList.reversed()
    }

    fun getTypeList():List<TypeModel>{
        if(typeList.isEmpty()){
            typeList.add(TypeModel("movie", "Movie"))
            typeList.add(TypeModel("podcastEpisode", "Podcast Episode"))
            typeList.add(TypeModel("musicVideo", "Music Video"))
            typeList.add(TypeModel("podcastSeries", "Podcast Series"))
            typeList.add(TypeModel("short", "Short"))
            typeList.add(TypeModel("tvEpisode", "Tv Episode"))
            typeList.add(TypeModel("tvMiniSeries", "Tv Mini Series"))
            typeList.add(TypeModel("tvMovie", "Tv Movie"))
            typeList.add(TypeModel("tvPilot", "Tv Pilot"))
            typeList.add(TypeModel("tvSeries", "Tv Series"))
            typeList.add(TypeModel("tvShort", "Tv Short"))
            typeList.add(TypeModel("tvSpecial", "Tv Special"))

            typeList.add(TypeModel("video", "Video"))
            typeList.add(TypeModel("videoGame", "Video Game"))
        }
        return typeList
    }
    fun getGenreList():List<String>{
        if(genreList.isEmpty()){
            genreList.add("Action")
            genreList.add("Adult")
            genreList.add("Adventure")
            genreList.add("Animation")
            genreList.add("Biography")
            genreList.add("Comedy")
            genreList.add("Crime")
            genreList.add("Documentary")
            genreList.add("Drama")
            genreList.add("Family")
            genreList.add("Fantasy")
            genreList.add("Film-Noir")
            genreList.add("Game-Show")
            genreList.add("History")
            genreList.add("Horror")
            genreList.add("Music")
            genreList.add("Musical")
            genreList.add("Mystery")
            genreList.add("News")
            genreList.add("Reality-TV")
            genreList.add("Romance")
            genreList.add("Sci-Fi")
            genreList.add("Short")
            genreList.add("Sport")
            genreList.add("Talk-Show")
            genreList.add("Thriller")
            genreList.add("War")
            genreList.add("Western")
        }
        return genreList
    }
}