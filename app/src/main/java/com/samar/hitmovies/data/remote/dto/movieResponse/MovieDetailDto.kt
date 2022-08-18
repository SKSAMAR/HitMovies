package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class MovieDetailDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("primaryImage")
    val primaryImage: PrimaryImage?,
    @SerializedName("releaseDate")
    val releaseDate: ReleaseDate,
    @SerializedName("releaseYear")
    val releaseYear: ReleaseYear?,
    @SerializedName("titleText")
    val titleText: TitleText,
    @SerializedName("titleType")
    val titleType: TitleType
)