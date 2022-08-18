package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class Caption(
    @SerializedName("plainText")
    val plainText: String,
    @SerializedName("__typename")
    val typename: String
)