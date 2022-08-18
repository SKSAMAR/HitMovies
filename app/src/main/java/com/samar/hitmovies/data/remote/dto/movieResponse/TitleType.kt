package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class TitleType(
    @SerializedName("id")
    val id: String,
    @SerializedName("isEpisode")
    val isEpisode: Boolean,
    @SerializedName("isSeries")
    val isSeries: Boolean,
    @SerializedName("text")
    val text: String,
    @SerializedName("__typename")
    val typename: String
)