package com.samar.hitmovies.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.samar.hitmovies.domain.model.Genre

data class GenreDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("uuid")
    val uuid: String
)

fun GenreDto.toGenre(): Genre{
    return Genre(
        name = name,
        uuid = uuid
    )
}