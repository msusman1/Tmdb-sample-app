package com.tmdb.app.data.response

import com.google.gson.annotations.SerializedName

data class ApiSeasonDetailResponse(

    @SerializedName("air_date") var airDate: String? = null,
    @SerializedName("episodes") var episodes: ArrayList<ApiEpisodes> = arrayListOf(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("season_number") var seasonNumber: Int? = null

)


data class ApiEpisodes(

    @SerializedName("air_date") var airDate: String? = null,
    @SerializedName("episode_number") var episodeNumber: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("production_code") var productionCode: String? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("season_number") var seasonNumber: Int? = null,
    @SerializedName("show_id") var showId: Int? = null,
    @SerializedName("still_path") var stillPath: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("crew") var crew: ArrayList<ApiGuestStar> = arrayListOf(),
    @SerializedName("guest_stars") var guestStars: ArrayList<ApiGuestStar> = arrayListOf()

)

data class ApiGuestStar(
    @SerializedName("job") var job: String? = null,
    @SerializedName("department") var department: String? = null,
    @SerializedName("credit_id") var creditId: String? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("gender") var gender: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("known_for_department") var knownForDepartment: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("profile_path") var profilePath: String? = null
)