package com.showcase.movie.util

import android.util.Log
import com.hino.hearts.BuildConfig

class Logging {
    fun Log(tag: String, logMsg: String) {
        if (BuildConfig.BUILD_TYPE == "debug")
            Log.d(tag, logMsg)
    }
}