package com.tmdb.app.data.response

import com.google.gson.annotations.SerializedName

data class ApiTv(
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("first_air_date") var firstAirDate: String? = null,
    @SerializedName("last_air_date") var lastAirDate: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("genres") var genres: ArrayList<ApiGenres> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("origin_country") var originCountry: ArrayList<String> = arrayListOf(),
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("images") var images: ApiImages? = null,
    @SerializedName("status") var status: String? = null,
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("seasons") var seasons: List<ApiSeason>? = null,
    @SerializedName("videos") var videos: ApiVideos? = null,
    @SerializedName("type") var type: String? = null,

    )

data class ApiSeason(
    @SerializedName("air_date") val air_date: String?=null,
    @SerializedName("episode_count") val episode_count: Int?=null,
    @SerializedName("id") val id: Int?=null,
    @SerializedName("name") val name: String?=null,
    @SerializedName("overview") val overview: String?=null,
    @SerializedName("poster_path") val poster_path: String?=null,
    @SerializedName("season_number") val season_number: Int?=null,
)