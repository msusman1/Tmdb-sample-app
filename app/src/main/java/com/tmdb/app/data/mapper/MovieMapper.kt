package com.tmdb.app.data.mapper

import com.tmdb.app.data.UrlProvider
import com.tmdb.app.data.response.ApiImages
import com.tmdb.app.data.response.ApiMovie
import com.tmdb.app.data.response.ApiVideos
import com.tmdb.app.data.response.MediaType
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.entity.Video
import javax.inject.Inject

class MovieMapper @Inject constructor(
    private val urlProvider: UrlProvider
) {

    private fun ApiVideos.mapToDomain(): List<Video> {
        return this.videos?.map {
            Video(
                iso6391 = it.iso6391 ?: "",
                iso31661 = it.iso31661 ?: "",
                name = it.name ?: "",
                key = it.key ?: "",
                site = it.site ?: "",
                size = it.size ?: 0,
                type = it.type ?: "",
                official = it.official ?: false,
                publishedAt = it.publishedAt ?: "",
                id = it.id ?: "",
            )
        } ?: emptyList()

    }


    fun mapToDomain(apiMovie: ApiMovie): Movie {
        return Movie(
            adult = apiMovie.adult ?: false,
            backdropPath = urlProvider.getBackdropFullUrl(apiMovie.backdropPath),
            budget = apiMovie.budget ?: 0,
            genres = apiMovie.genres?.map { it.name } ?: emptyList(),
            homepage = apiMovie.homepage ?: "",
            id = apiMovie.id ?: -1,
            originalLanguage = apiMovie.originalLanguage ?: "",
            originalTitle = apiMovie.originalTitle ?: "",
            overview = apiMovie.overview ?: "",
            popularity = apiMovie.popularity ?: 0.0,
            posterPath = urlProvider.getPosterFullUrl(apiMovie.posterPath),
            productionCompanies = apiMovie.productionCompanies?.map { it.name }
                ?: emptyList(),
            productionCountries = apiMovie.productionCountries?.map { it.name }
                ?: emptyList(),
            releaseDate = apiMovie.releaseDate ?: "",
            revenue = apiMovie.revenue ?: 0,
            runtime = apiMovie.runtime ?: 0,
            spokenLanguages = apiMovie.spokenLanguages?.map { it.name } ?: emptyList(),
            status = apiMovie.status ?: "",
            tagline = apiMovie.tagline ?: "",
            title = apiMovie.title ?: "",
            video = apiMovie.video ?: false,
            voteAverage = apiMovie.voteAverage ?: 0.0,
            voteCount = apiMovie.voteCount ?: 0,
            trailer = urlProvider.getYoutubeTrailer(apiMovie.videos) ?: "",
            logo = apiMovie.images?.getLogo() ?: "",
            posters = apiMovie.images?.getPosters() ?: emptyList(),
            backdrops = apiMovie.images?.getBackdrops() ?: emptyList(),
            mediaType = if (apiMovie.mediaType == "tv") MediaType.TV else MediaType.MOVIE
        )
    }

    private fun ApiImages.getLogo(): String? {
        return logos?.firstOrNull()?.filePath
    }

    private fun ApiImages.getPosters(): List<String>? {
        return posters?.map { urlProvider.getPosterFullUrl(it.filePath) }?.filterNotNull()
    }

    private fun ApiImages.getBackdrops(): List<String>? {
        return backdrops?.map { urlProvider.getBackdropFullUrl(it.filePath) }?.filterNotNull()
    }

}