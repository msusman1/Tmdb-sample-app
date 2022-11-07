package com.tmdb.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.usecase.movie.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getLatestMovieUseCase: GetLatestMovieUseCase,
    private val getTrendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
) : ViewModel() {
    val trendingMovies: Flow<PagingData<Movie>> =
        getTrendingMoviesUseCase(Unit).cachedIn(viewModelScope)
    val nowPlayingMovies: Flow<PagingData<Movie>> =
        getNowPlayingMoviesUseCase(Unit).cachedIn(viewModelScope)
    val popularMovies: Flow<PagingData<Movie>> = getPopularMoviesUseCase(Unit).cachedIn(viewModelScope)
    val topRatedMovies: Flow<PagingData<Movie>> =
        getTopRatedMoviesUseCase(Unit).cachedIn(viewModelScope)
    val upcomingMovies: Flow<PagingData<Movie>> =
        getUpcomingMoviesUseCase(Unit).cachedIn(viewModelScope)

}

enum class MovieLoadType(var title: String) {
    TRENDING("Trending"),
    NOW_PLAYING("Now playing"),
    POPULAR("Popular"),
    TOP_RATED("Top Rated"),
    UPCOMING("Upcoming"),
}
