package com.tmdb.app.data.tv

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.app.data.TvApi
import com.tmdb.app.data.response.ApiMovie
import com.tmdb.app.data.response.ApiTv
import com.tmdb.app.data.response.PagedResponse
import com.tmdb.app.ui.home.MovieLoadType
import com.tmdb.app.ui.home.TVLoadType
import timber.log.Timber

class TvPagingSource(
    private val tvApi: TvApi,
    private val tvLoadType: TVLoadType
) : PagingSource<Int, ApiTv>() {
    override fun getRefreshKey(state: PagingState<Int, ApiTv>): Int? {
        if (state.anchorPosition != null) {
            val closestPage = state.closestPageToPosition(state.anchorPosition!!)
            return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiTv> {
        return try {
            val pageNo = params.key ?: 1
            val pagedResponse = getMoviesToLoad(pageNo)
            //current page is the last page so pass the nextKey to null
            val nextPageNo = if (pagedResponse.pageNumber == pagedResponse.totalPages) {
                null
            } else {
                pagedResponse.pageNumber + 1
            }
            LoadResult.Page(
                data = pagedResponse.results, prevKey = null, nextKey = nextPageNo
            )
        } catch (e: Exception) {
            Timber.d(e.message)
            LoadResult.Error(e)
        }

    }

    private suspend fun getMoviesToLoad(pageNo: Int): PagedResponse<ApiTv> {
        return when (tvLoadType) {
            TVLoadType.TRENDING -> tvApi.getTrendingTv(pageNo)
            TVLoadType.POPULAR -> tvApi.getPopularTvs(pageNo)
            TVLoadType.AIRING_TODAY -> tvApi.getAiringToday(pageNo)
            TVLoadType.ON_THE_AIR -> tvApi.getOntheAir(pageNo)
            TVLoadType.TOP_RATED -> tvApi.getTopRatedTvs(pageNo)
        }
    }
}