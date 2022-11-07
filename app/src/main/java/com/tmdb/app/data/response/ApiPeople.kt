package com.tmdb.app.data.response

import com.google.gson.annotations.SerializedName

data class ApiPeople(
    @SerializedName("adult")
    var adult: Boolean? = null,

    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("known_for")
    var knownFor: ArrayList<ApiKnownFor>? = null,

    @SerializedName("known_for_department")
    var knownForDepartment: String? = null,

    @SerializedName("also_known_as")
    var alsoKnownAs: List<String>? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("popularity")
    var popularity: Double? = null,

    @SerializedName("profile_path")
    var profilePath: String? = null,

    @SerializedName("birthday")
    var birthday: String? = null,

    @SerializedName("deathDay")
    var deathDay: String? = null,

    @SerializedName("place_of_birth")
    var placeOfBirth: String? = null,

    @SerializedName("homepage")
    var homepage: String? = null,

    @SerializedName("biography")
    var biography: String? = null,

    @SerializedName("images")
    var images: ApiImages? = null,

    @SerializedName("combined_credits")
    var combinedCredits: ApiCombinedCredits? = null
)

data class ApiCombinedCredits(
    @SerializedName("cast") var cast: List<ApiMovie>? = null,
    @SerializedName("crew") var crew: List<ApiMovie>? = null,

)
data class ApiKnownFor(

    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("media_type") var mediaType: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null

)