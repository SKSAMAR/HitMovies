package com.samar.hitmovies.common

import android.os.SystemClock

object Constants {

    const val BASE_URL = "https://moviesdatabase.p.rapidapi.com"
    const val MovieData = "movieData"

    private var mLastClickTime: Long = 0
    fun isAccessable(): Boolean {
        return if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
            false
        } else {
            mLastClickTime = SystemClock.elapsedRealtime()
            true
        }
    }
}