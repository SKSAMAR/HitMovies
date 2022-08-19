package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName
import com.samar.hitmovies.domain.model.MovieDetail
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

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        year = releaseDate?.year,
        releaseYear = releaseYear?.year,
        endYear = releaseYear?.endYear,
        releaseDate = releaseDate?.day,
        releaseMonth = releaseDate?.month,
        releaseOnYear = releaseDate?.year,
        title = titleText.text,
        captionText = primaryImage?.caption?.plainText,
        imageUrl = primaryImage?.url,
        typeCode = titleType?.id,
        typeName = titleType?.typename,
    )
}