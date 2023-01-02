package com.tmdb.app.data.mapper

import com.tmdb.app.data.UrlProvider
import com.tmdb.app.data.response.*
import com.tmdb.app.domain.entity.*
import javax.inject.Inject

class TvMapper @Inject constructor(private val urlProvider: UrlProvider) {

    fun mapToDomain(apiTv: ApiTv): TV {
        return TV(
            id = apiTv.id ?: -1,
            title = apiTv.name ?: "",
            backdrops = apiTv.images?.getBackdrops() ?: emptyList(),
            firstAirDate = apiTv.firstAirDate ?: "",
            lastAirDate = apiTv.lastAirDate ?: "",
            popularity = apiTv.popularity ?: 0.0,
            originalLanguage = apiTv.originalLanguage ?: "",
            status = apiTv.status ?: "",
            genres = apiTv.genres.map { it.name },
            overview = apiTv.overview ?: "",
            homepage = apiTv.homepage ?: "",
            poster = urlProvider.getPosterFullUrl(apiTv.posterPath)  ,
            trailer = urlProvider.getYoutubeTrailer(apiTv.videos) ?: "",
            type = apiTv.type ?: "",
            seasons = apiTv.seasons?.map { mapToDomain(it) } ?: emptyList(),

            )
    }

    private fun ApiImages.getPosters(): List<String>? {
        return posters?.map { urlProvider.getBackdropFullUrl(it.filePath) }
    }

    private fun ApiImages.getBackdrops(): List<String>? {
        return backdrops?.map { urlProvider.getBackdropFullUrl(it.filePath) }
    }

    fun mapToDomain(apiSeason: ApiSeason): Season {
        return Season(
            title = apiSeason.name ?: "",
            seasonNumber = apiSeason.season_number ?: -1,
            poster = urlProvider.getPosterFullUrl(apiSeason.poster_path),
        )
    }

    fun mapToDomain(apiEpisode: ApiEpisodes): Episode {
        return Episode(
            id = apiEpisode.id ?: -1,
            title = apiEpisode.name ?: "",
            overview = apiEpisode.overview ?: "",
            backdrop = urlProvider.getBackdropFullUrl(apiEpisode.stillPath),
            runtime = apiEpisode.runtime ?: 0,
        )
    }

}