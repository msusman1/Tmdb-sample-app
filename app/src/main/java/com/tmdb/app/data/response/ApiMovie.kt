package com.tmdb.app.data.response

import com.google.gson.annotations.SerializedName
import okhttp3.MediaType

data class ApiMovie(
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("budget") var budget: Long? = null,
    @SerializedName("genres") var genres: ArrayList<ApiGenres>? = null,
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_companies") var productionCompanies: ArrayList<ApiProductionCompanies>? = null,
    @SerializedName("production_countries") var productionCountries: ArrayList<ApiProductionCountries>? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("revenue") var revenue: Long? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("spoken_languages") var spokenLanguages: ArrayList<ApiSpokenLanguages>? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("videos") var videos: ApiVideos? = ApiVideos(),
    @SerializedName("images") var images: ApiImages? = ApiImages(),
    @SerializedName("media_type") var mediaType: String?
)

enum class MediaType {
    MOVIE, TV
}

data class ApiVideo(

    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("key") var key: String? = null,
    @SerializedName("site") var site: String? = null,
    @SerializedName("size") var size: Int? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("official") var official: Boolean? = null,
    @SerializedName("published_at") var publishedAt: String? = null,
    @SerializedName("id") var id: String? = null

)

data class ApiVideos(

    @SerializedName("results") var videos: ArrayList<ApiVideo>? = null

)

data class ApiSpokenLanguages(

    @SerializedName("english_name") var englishName: String,
    @SerializedName("iso_639_1") var iso6391: String,
    @SerializedName("name") var name: String,

)


data class ApiImages(
    @SerializedName("backdrops") var backdrops: ArrayList<ApiImage>? = null,
    @SerializedName("logos") var logos: ArrayList<ApiImage>? = null,
    @SerializedName("posters") var posters: ArrayList<ApiImage>? = null,
    @SerializedName("profiles") var profiles: ArrayList<ApiImage>? = null,
)

data class ApiProductionCompanies(

    @SerializedName("id") var id: Int,
    @SerializedName("logo_path") var logoPath: String? = null,
    @SerializedName("name") var name: String,
    @SerializedName("origin_country") var originCountry: String? = null

)

data class ApiProductionCountries(

    @SerializedName("iso_3166_1") var iso31661: String,
    @SerializedName("name") var name: String,

    )


data class ApiGenres(

    @SerializedName("id") var id: Int,
    @SerializedName("name") var name: String

)