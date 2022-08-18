package com.samar.hitmovies.domain.model

data class TypeModel(
    val typeCode: String,
    val displayName: String
){
    override fun toString(): String {
        return displayName
    }
}


