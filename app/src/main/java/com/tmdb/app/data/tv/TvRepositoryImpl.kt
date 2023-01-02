package com.tmdb.app.data.tv

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tmdb.app.data.TvApi
import com.tmdb.app.data.mapper.TvMapper
import com.tmdb.app.data.response.ApiSeason
import com.tmdb.app.data.response.ApiTv
import com.tmdb.app.domain.entity.Episode
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.entity.Season
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
        val apiTv = tvApi.getTvDetail(tvId, "videos,images")
        return tvMapper.mapToDomain(apiTv)
    }

    override suspend fun getSeasonDetail(
        tvId: Int,
        seasonNumber: Int
    ): Pair<Season, List<Episode>> {
        val seasonDetailResponse = tvApi.getSeasonDetail(tvId, seasonNumber)
        val apiSeason = ApiSeason(
            air_date = seasonDetailResponse.airDate,
            episode_count = seasonDetailResponse.episodes.count(),
            id = seasonDetailResponse.id,
            name = seasonDetailResponse.name,
            overview = seasonDetailResponse.overview,
            poster_path = seasonDetailResponse.posterPath,
            season_number = seasonDetailResponse.seasonNumber,
        )
        val season = tvMapper.mapToDomain(apiSeason)
        return season to seasonDetailResponse.episodes.map {
            tvMapper.mapToDomain(it)
        }

    }
}