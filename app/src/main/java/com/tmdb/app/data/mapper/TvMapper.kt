package com.tmdb.app.data.mapper

import com.tmdb.app.data.response.*
import com.tmdb.app.domain.entity.*
import javax.inject.Inject

class TvMapper @Inject constructor() {

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

    private fun ApiSpokenLanguages.mapToDomain(): SpokenLanguages {
        return SpokenLanguages(
            englishName = this.englishName ?: "",
            iso6391 = this.iso6391 ?: "",
            name = this.name ?: "",
        )
    }

    private fun ApiProductionCompanies.mapToDomain(): ProductionCompanies {
        return ProductionCompanies(
            id = this.id ?: -1,
            logoPath = this.logoPath ?: "",
            name = this.name ?: "",
            originCountry = this.originCountry ?: "",
        )
    }

    private fun ApiProductionCountries.mapToDomain(): ProductionCountries {
        return ProductionCountries(
            iso31661 = this.iso31661 ?: "",
            name = this.name ?: "",
        )
    }

    private fun ApiGenres.mapToDomain(): Genres {
        return Genres(this.id ?: -1, this.name ?: "")
    }

    fun mapToDomain(apiTv: ApiTv): TV {
        return TV(
            id = apiTv.id ?: -1,
            title = apiTv.name ?: "",
            backdropPath = apiTv.backdropPath ?: "",
            firstAirDate = apiTv.firstAirDate ?: "",
            genreIds = apiTv.genreIds ?: emptyList(),
            originCountry = apiTv.originCountry ?: emptyList(),
            originalLanguage = apiTv.originalLanguage ?: "",
            originalName = apiTv.originalName ?: "",
            overview = apiTv.overview ?: "",
            popularity = apiTv.popularity ?: 0.0,
            posterPath = apiTv.posterPath ?: "",
            voteAverage = apiTv.voteAverage ?: 0.0,
            voteCount = apiTv.voteCount ?: 0,

            )
    }

    private fun ApiImages.getLogo(): String? {
        return logos?.firstOrNull()?.filePath
    }

    private fun ApiImages.getPosters(): List<String>? {
        return posters?.map { it.filePath }?.filterNotNull()
    }

    private fun ApiImages.getBackdrops(): List<String>? {
        return backdrops?.map { it.filePath }?.filterNotNull()
    }

}