package com.tmdb.app.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tmdb.app.data.MovieApi
import com.tmdb.app.data.mapper.MovieMapper
import com.tmdb.app.data.response.ApiMovie
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.repository.movie.MovieRepository
import com.tmdb.app.ui.home.MovieLoadType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val MOVIE_PAGE_SIZE = 20

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieMapper: MovieMapper
) : MovieRepository {
    override suspend fun getLatestMovie(): Movie {
        val apiMovie = movieApi.getLatestMovie()
        return movieMapper.mapToDomain(apiMovie)
    }


    private fun Flow<PagingData<ApiMovie>>.mapToDomain(): Flow<PagingData<Movie>> {
        return map { it.map { movieMapper.mapToDomain(it) } }
    }

    override fun getTrending() = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { MoviePagingSource(movieApi, MovieLoadType.TRENDING) }
    ).flow.mapToDomain()

    override fun getNowPlaying() = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { MoviePagingSource(movieApi, MovieLoadType.NOW_PLAYING) }
    ).flow.mapToDomain()

    override fun getPopular() = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { MoviePagingSource(movieApi, MovieLoadType.POPULAR) }
    ).flow.mapToDomain()

    override fun getTopRated() = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { MoviePagingSource(movieApi, MovieLoadType.TOP_RATED) }
    ).flow.mapToDomain()

    override fun getUpcoming() = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { MoviePagingSource(movieApi, MovieLoadType.UPCOMING) }
    ).flow.mapToDomain()

    override fun getRelated(movieId: Int) = Pager(config = PagingConfig(pageSize = MOVIE_PAGE_SIZE),
        pagingSourceFactory = { RelatedMoviesPagingSource(movieApi, movieId) }).flow.mapToDomain()

    override suspend fun getMovieDetail(movieId: Int): Movie {
        val apiMovie = movieApi.getMovieDetail(movieId,"videos,images")
        return movieMapper.mapToDomain(apiMovie)
    }
}