package com.samar.hitmovies.domain.model

import com.samar.hitmovies.data.remote.dto.GenreDto

data class Genre(
    val name: String,
    val uuid: String
)


fun Genre.toGenreDto(): GenreDto{
    return GenreDto(
        name = name,
        uuid = uuid
    )
}
