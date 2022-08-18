package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TitleText(
    @SerializedName("text")
    val text: String,
    @SerializedName("__typename")
    val typename: String
): Serializable