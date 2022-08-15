package com.samar.hitmovies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("messageStatus")
    val messageStatus: String,
    @SerializedName("results")
    val results: List<GenreDto>,
    @SerializedName("status")
    var status: Int = 400,
    @SerializedName("success")
    var success: Boolean = false
)