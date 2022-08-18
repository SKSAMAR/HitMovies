package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class MovieFetchResponse(
    @SerializedName("entries")
    val entries: Int,
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("page")
    val page: String,
    @SerializedName("results")
    val results: ArrayList<MovieDetailDto>
)