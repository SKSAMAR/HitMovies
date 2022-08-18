package com.samar.hitmovies.data.remote.dto.movieResponse


import com.google.gson.annotations.SerializedName

data class PrimaryImage(
    @SerializedName("caption")
    val caption: Caption,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("__typename")
    val typename: String,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int
)