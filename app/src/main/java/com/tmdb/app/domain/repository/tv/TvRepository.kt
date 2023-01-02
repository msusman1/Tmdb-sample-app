package com.tmdb.app.domain.repository.tv

import androidx.paging.PagingData
import com.tmdb.app.domain.entity.Episode
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.entity.Season
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTrending(): Flow<PagingData<TV>>
    fun getPopular(): Flow<PagingData<TV>>
    fun getAiringToday(): Flow<PagingData<TV>>
    fun getOnAir(): Flow<PagingData<TV>>
    fun getTopRated(): Flow<PagingData<TV>>
    suspend fun getTvDetail(tvId: Int): TV
    suspend fun getSeasonDetail(tvId: Int, seasonNumber: Int): Pair<Season, List<Episode>>
}