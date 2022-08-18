package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class TitleText(
    @SerializedName("text")
    val text: String,
    @SerializedName("__typename")
    val typename: String
)