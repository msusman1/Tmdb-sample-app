package com.tmdb.app.data.mapper

import com.tmdb.app.data.UrlProvider
import com.tmdb.app.data.response.*
import com.tmdb.app.domain.entity.*
import javax.inject.Inject

class PeopleMapper @Inject constructor(
    val movieMapper: MovieMapper,
    private val urlProvider: UrlProvider

) {


    fun ApiKnownFor.mapToDomain(): PeopleKnownFor {
        return PeopleKnownFor(
            adult = this.adult ?: false,
            backdropPath = urlProvider.getBackdropFullUrl(this.backdropPath),
            genreIds = this.genreIds,
            id = this.id ?: -1,
            mediaType = this.mediaType ?: "",
            originalLanguage = this.originalLanguage ?: "",
            originalTitle = this.originalTitle ?: "",
            overview = this.overview ?: "",
            posterPath = urlProvider.getBackdropFullUrl(this.posterPath),
            releaseDate = this.releaseDate ?: "",
            title = this.title ?: "",
            video = this.video ?: false,
            voteAverage = this.voteAverage ?: 0.0,
            voteCount = this.voteCount ?: 0,
        )
    }

    fun mapToDomain(apiPeople: ApiPeople): People {
        return People(
            id = apiPeople.id ?: -1,
            name = apiPeople.name ?: "",
            adult = apiPeople.adult ?: false,
            gender = apiPeople.gender.mapGender(),
            knownFor = apiPeople.knownFor?.map { it.mapToDomain() } ?: emptyList(),
            knownForDepartment = apiPeople.knownForDepartment ?: "",
            alsoKnownAs = apiPeople.alsoKnownAs ?: emptyList(),
            popularity = apiPeople.popularity ?: 0.0,
            profileUrl = urlProvider.getPosterFullUrl(apiPeople.profilePath),
            birthday = apiPeople.birthday ?: "",
            placeOfBirth = apiPeople.placeOfBirth ?: "",
            homepage = apiPeople.homepage ?: "",
            biography = apiPeople.biography ?: "",
            deathday = apiPeople.deathDay ?: "",
            images = apiPeople.images?.profiles?.mapNotNull { urlProvider.getPosterFullUrl(it.filePath) }?: emptyList(),
            cast = apiPeople.combinedCredits?.cast?.map { movieMapper.mapToDomain(it) }
                ?: emptyList(),
            crew = apiPeople.combinedCredits?.crew?.map { movieMapper.mapToDomain(it) }
                ?: emptyList(),

            )
    }

    private fun String?.mapGender(): Gender {
        return when (this) {
            "FEMALE" -> Gender.FEMALE
            "MALE" -> Gender.MALE
            else -> Gender.OTHER
        }
    }

}