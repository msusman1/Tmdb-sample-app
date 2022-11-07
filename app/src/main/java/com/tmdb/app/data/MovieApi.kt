package com.tmdb.app.data

import com.tmdb.app.data.response.ApiMovie
import com.tmdb.app.data.response.PagedResponse
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject


interface MovieApi {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(@Query("page") pageNo: Int): PagedResponse<ApiMovie>

    @GET("movie/latest")
    suspend fun getLatestMovie(): ApiMovie

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") pageNo: Int): PagedResponse<ApiMovie>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") pageNo: Int): PagedResponse<ApiMovie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(@Query("page") pageNo: Int): PagedResponse<ApiMovie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("page") pageNo: Int): PagedResponse<ApiMovie>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String
    ): ApiMovie

    @GET("movie/{movie_id}/similar")
    suspend fun getRelatedMovies(
        @Path("movie_id") movieId: Int, @Query("page") pageNo: Int
    ): PagedResponse<ApiMovie>

}
