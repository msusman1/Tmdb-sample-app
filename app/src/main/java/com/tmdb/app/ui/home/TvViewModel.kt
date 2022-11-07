package com.tmdb.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.usecase.movie.*
import com.tmdb.app.domain.usecase.tv.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TvViewModel @Inject constructor(
    private val getTrendingTvUseCase: GetTrendingTvsUseCase,
    private val getPopularTvUseCase: GetPopularTvsUseCase,
    private val getAiringTodayTvUseCase: GetAiringTodayTvsUseCase,
    private val getOnTheAirTvUseCase: GetOnAirTvsUseCase,
    private val getTopRatedTvUseCase: GetTopRatedTvsUseCase,
) : ViewModel() {
    val trendingTvs: Flow<PagingData<TV>> =
        getTrendingTvUseCase(Unit).cachedIn(viewModelScope)
    val popularTvs: Flow<PagingData<TV>> =
        getPopularTvUseCase(Unit).cachedIn(viewModelScope)
    val airingTodayTvs: Flow<PagingData<TV>> =
        getAiringTodayTvUseCase(Unit).cachedIn(viewModelScope)
    val onTheAirTvs: Flow<PagingData<TV>> =
        getOnTheAirTvUseCase(Unit).cachedIn(viewModelScope)
    val topRatedTvs: Flow<PagingData<TV>> =
        getTopRatedTvUseCase(Unit).cachedIn(viewModelScope)

}

enum class TVLoadType(var title: String) {
    TRENDING("Trending"),
    POPULAR("Popular"),
    AIRING_TODAY("Airing Today"),
    ON_THE_AIR("On The Air"),
    TOP_RATED("Top Rated"),
}
