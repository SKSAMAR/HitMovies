package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReleaseDate(
    @SerializedName("day")
    val day: Int,
    @SerializedName("month")
    val month: Int,
    @SerializedName("__typename")
    val typename: String,
    @SerializedName("year")
    val year: Int
): Serializable