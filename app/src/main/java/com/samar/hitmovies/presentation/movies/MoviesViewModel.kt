package com.samar.hitmovies.presentation.movies

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.samar.hitmovies.common.BaseViewModel
import com.samar.hitmovies.common.Constants.isAccessable
import com.samar.hitmovies.common.Resource
import com.samar.hitmovies.data.remote.dto.movieResponse.MovieDetailDto
import com.samar.hitmovies.domain.model.ScreenState
import com.samar.hitmovies.domain.model.TypeModel
import com.samar.hitmovies.domain.uses_cases.getMovies.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel
@Inject constructor( private val getMoviesUseCase: GetMoviesUseCase): BaseViewModel<ArrayList<MovieDetailDto>>() {

    val lazyGridState = LazyGridState()
    val movieTitle = mutableStateOf("")
    var next: String? = null
    var genre = mutableStateOf("Comedy")
    var type = mutableStateOf(TypeModel("all", "All"))
    var year = mutableStateOf(2022)
    private val yearList = ArrayList<Int>()
    private val typeList = ArrayList<TypeModel>()
    private val genreList = ArrayList<String>()

    init {
        getMovies()
    }
    fun getMovies(page: Int = 1){

        if(movieTitle.value.isNotEmpty()){
            getMoviesByName()
            return
        }

        var baseConnUrl = "/titles?"
        if(type.value.typeCode.lowercase()!="all"){
            baseConnUrl+="titleType="+type.value.typeCode
        }
        if(year.value!=0){
            baseConnUrl+="&year="+year.value
        }
        if(genre.value.lowercase()!="all"){
            baseConnUrl+="&genre="+genre.value
        }
        baseConnUrl+="&page=$page&limit=10"
        getMoviesUseCase.invoke(baseConnUrl).onEach {
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
        if(isAccessable()){
            next?.let {
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
        page: Int = 1,
    ){
        if(movieTitle.value.isEmpty()){
            getMovies()
            return
        }

        var baseConnUrl = "/titles/search/title/${movieTitle.value}?page=$page&info=mini_info&limit=10"

        if(type.value.typeCode.lowercase()!="all"){
            baseConnUrl+="&titleType="+type.value.typeCode
        }
        if(year.value!=0){
            baseConnUrl+="&year="+year.value
        }
        if(genre.value.lowercase()!="all"){
            baseConnUrl+="&genre="+genre.value
        }


        getMoviesUseCase.getMoviesByTitle(baseConnUrl).onEach {
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
            yearList.add(0)
        }
        return yearList.reversed()
    }

    fun getTypeList():List<TypeModel>{
        if(typeList.isEmpty()){
            typeList.add(TypeModel("all", "All"))
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
            genreList.add("All")
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