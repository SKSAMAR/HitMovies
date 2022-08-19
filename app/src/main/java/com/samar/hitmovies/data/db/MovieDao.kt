package com.samar.hitmovies.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.samar.hitmovies.domain.model.MovieDetail

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(movieDetail: MovieDetail): Long?

    @Delete
    suspend fun deleteFromFavourite(movieDetail: MovieDetail): Int?

    @Query("SELECT * FROM movies ORDER BY timing DESC")
    fun getAllContent(): LiveData<List<MovieDetail>>


}