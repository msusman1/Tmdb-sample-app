package com.tmdb.app.data

import com.tmdb.app.data.response.ApiVideos
import javax.inject.Inject

class UrlProvider @Inject constructor() {

    private val BASE_URL = "https://api.themoviedb.org/3/"
    private val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    private val BACKDROP_BASE_URL = "https://image.tmdb.org/t/p/w780/"
    private val BASE_URL_YOUTUBE = "https://www.youtube.com/watch?v="
    fun provideBaseUrl(): String {
        return BASE_URL
    }

    fun provideBackdropUrl(): String {
        return BACKDROP_BASE_URL
    }

    fun providePosterUrl(): String {
        return POSTER_BASE_URL
    }

    fun getPosterFullUrl(path: String?): String {
        return "$POSTER_BASE_URL$path"
    }

    fun getBackdropFullUrl(path: String?): String {
        return "$BACKDROP_BASE_URL$path"
    }

    fun getYoutubeTrailer(videos: ApiVideos?): String? {
        val youtubeVideoId =
            videos?.videos?.filter { it.site == "YouTube" && it.type == "Trailer" }?.firstOrNull()?.key
        if (youtubeVideoId != null) {
            return "$BASE_URL_YOUTUBE$youtubeVideoId"
        }
        return null
    }
}