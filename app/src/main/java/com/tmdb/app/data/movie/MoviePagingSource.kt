package com.tmdb.app.data.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.app.data.MovieApi
import com.tmdb.app.data.response.ApiMovie
import com.tmdb.app.data.response.PagedResponse
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.ui.home.MovieLoadType
import timber.log.Timber

class MoviePagingSource(
    private val movieApi: MovieApi,
    private val movieLoadType: MovieLoadType
) : PagingSource<Int, ApiMovie>() {
    override fun getRefreshKey(state: PagingState<Int, ApiMovie>): Int? {
        if (state.anchorPosition != null) {
            val closestPage = state.closestPageToPosition(state.anchorPosition!!)
            return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiMovie> {
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

    private suspend fun getMoviesToLoad(pageNo: Int): PagedResponse<ApiMovie> {
        return when (movieLoadType) {
            MovieLoadType.TRENDING -> movieApi.getTrendingMovies(pageNo)
            MovieLoadType.NOW_PLAYING -> movieApi.getNowPlayingMovies(pageNo)
            MovieLoadType.POPULAR -> movieApi.getPopularMovies(pageNo)
            MovieLoadType.TOP_RATED -> movieApi.getTopRatedMovies(pageNo)
            MovieLoadType.UPCOMING -> movieApi.getUpcomingMovies(pageNo)
        }
    }


}