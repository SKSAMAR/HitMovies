package com.samar.hitmovies.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.samar.hitmovies.data.remote.dto.movieResponse.*

@Entity(tableName = "movies")
data class MovieDetail(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val captionText: String?,
    val imageUrl: String?,
    val year: Int?,
    val endYear: Int?,
    val releaseOnYear: Int?,
    var typeName: String? = "All",
    var typeCode: String? = "all",
    val releaseDate: Int? = null,
    val releaseMonth: Int? = null,
    val releaseYear: Int? = null,
    val timing: Long = System.currentTimeMillis()
)

fun MovieDetail.toMovieDetailDto(): MovieDetailDto {
    return MovieDetailDto(
        id = id,
        primaryImage = PrimaryImage(
            url = imageUrl,
            caption = Caption(plainText = captionText, null),
            width = null,
            height = null,
            id = null,
            typename = null
        ),
        titleType = TitleType(
            text = typeName,
            id = typeCode,
            typename = null,
            isEpisode = null,
            isSeries = null,
        ),
        releaseDate = ReleaseDate(
            day = releaseDate,
            month = releaseMonth,
            year = releaseYear,
            typename = null
        ),
        releaseYear = ReleaseYear(
            endYear = endYear,
            year = year,
            typename = null
        ),
        titleText = TitleText(
            text = title,
            typename = null
        )
    )
}
