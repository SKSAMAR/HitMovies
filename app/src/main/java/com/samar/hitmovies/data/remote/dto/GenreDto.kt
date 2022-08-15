package com.samar.hitmovies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("uuid")
    val uuid: String
)