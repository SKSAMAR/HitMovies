package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieDetailDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("primaryImage")
    val primaryImage: PrimaryImage?,
    @SerializedName("releaseDate")
    val releaseDate: ReleaseDate?,
    @SerializedName("releaseYear")
    val releaseYear: ReleaseYear?,
    @SerializedName("titleText")
    val titleText: TitleText,
    @SerializedName("titleType")
    val titleType: TitleType?
): Serializable{
    override fun toString(): String {
        return "MovieDetailDto(id='$id', primaryImage=$primaryImage, releaseDate=$releaseDate, releaseYear=$releaseYear, titleText=$titleText, titleType=$titleType)"
    }
}