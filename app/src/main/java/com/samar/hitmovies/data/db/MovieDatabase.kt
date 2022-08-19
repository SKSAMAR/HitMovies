package com.samar.hitmovies.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samar.hitmovies.domain.model.MovieDetail

@Database(entities = [MovieDetail::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}