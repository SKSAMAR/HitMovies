package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ReleaseYear(
    @SerializedName("endYear")
    val endYear: Int?,
    @SerializedName("__typename")
    val typename: String?,
    @SerializedName("year")
    val year: Int?
): Serializable