package com.tmdb.app.data.tv

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tmdb.app.data.TvApi
import com.tmdb.app.data.mapper.TvMapper
import com.tmdb.app.data.response.ApiTv
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.repository.tv.TvRepository
import com.tmdb.app.ui.home.TVLoadType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val TV_PAGE_SIZE = 20

class TvRepositoryImpl @Inject constructor(
    private val tvApi: TvApi,
    private val tvMapper: TvMapper
) : TvRepository {


    private fun Flow<PagingData<ApiTv>>.mapToDomain(): Flow<PagingData<TV>> {
        return map { it.map { tvMapper.mapToDomain(it) } }
    }

    override fun getTrending() = Pager(
        config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { TvPagingSource(tvApi, TVLoadType.TRENDING) }
    ).flow.mapToDomain()

    override fun getPopular() = Pager(config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { TvPagingSource(tvApi, TVLoadType.POPULAR) }
    ).flow.mapToDomain()

    override fun getAiringToday() = Pager(config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { TvPagingSource(tvApi, TVLoadType.AIRING_TODAY) }
    ).flow.mapToDomain()


    override fun getOnAir() = Pager(config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { TvPagingSource(tvApi, TVLoadType.ON_THE_AIR) }
    ).flow.mapToDomain()

    override fun getTopRated() = Pager(config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { TvPagingSource(tvApi, TVLoadType.TOP_RATED) }
    ).flow.mapToDomain()


    override suspend fun getTvDetail(tvId: Int): TV {
        val apiTv = tvApi.getTvDetail(tvId)
        return tvMapper.mapToDomain(apiTv)
    }
}