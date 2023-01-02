package com.tmdb.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TMDBApp : Application() {
    companion object {
        lateinit var instance: TMDBApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}