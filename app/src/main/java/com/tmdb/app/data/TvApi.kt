package com.tmdb.app.data

import com.tmdb.app.data.response.ApiSeasonDetailResponse
import com.tmdb.app.data.response.ApiTv
import com.tmdb.app.data.response.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TvApi {

    @GET("trending/tv/day")
    suspend fun getTrendingTv(@Query("page") pageNo: Int): PagedResponse<ApiTv>

    @GET("tv/popular")
    suspend fun getPopularTvs(@Query("page") pageNo: Int): PagedResponse<ApiTv>

    @GET("tv/airing_today")
    suspend fun getAiringToday(@Query("page") pageNo: Int): PagedResponse<ApiTv>

    @GET("tv/on_the_air")
    suspend fun getOntheAir(@Query("page") pageNo: Int): PagedResponse<ApiTv>

    @GET("tv/top_rated")
    suspend fun getTopRatedTvs(@Query("page") pageNo: Int): PagedResponse<ApiTv>

    @GET("tv/{tvId}")
    suspend fun getTvDetail(
        @Path("tvId") tvId: Int, @Query("append_to_response") appendToResponse: String
    ): ApiTv

    @GET("tv/{tvId}/season/{seasonNumber}")
    suspend fun getSeasonDetail(
        @Path("tvId") tvId: Int,
        @Path("seasonNumber") seasonNumber: Int
    ): ApiSeasonDetailResponse


}
