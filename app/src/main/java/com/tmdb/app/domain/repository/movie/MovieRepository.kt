package com.tmdb.app.domain.repository.movie

import androidx.paging.PagingData
import com.tmdb.app.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getLatestMovie(): Movie
    fun getTrending(): Flow<PagingData<Movie>>
    fun getNowPlaying(): Flow<PagingData<Movie>>
    fun getPopular(): Flow<PagingData<Movie>>
    fun getTopRated(): Flow<PagingData<Movie>>
    fun getUpcoming(): Flow<PagingData<Movie>>
    fun getRelated(movieId: Int): Flow<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: Int): Movie
}