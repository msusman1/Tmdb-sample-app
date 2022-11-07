package com.tmdb.app.domain.repository.tv

import androidx.paging.PagingData
import com.tmdb.app.domain.entity.TV
import kotlinx.coroutines.flow.Flow

interface TvRepository {
    fun getTrending(): Flow<PagingData<TV>>
    fun getPopular(): Flow<PagingData<TV>>
    fun getAiringToday(): Flow<PagingData<TV>>
    fun getOnAir(): Flow<PagingData<TV>>
    fun getTopRated(): Flow<PagingData<TV>>
    suspend fun getTvDetail(tvId: Int): TV
}