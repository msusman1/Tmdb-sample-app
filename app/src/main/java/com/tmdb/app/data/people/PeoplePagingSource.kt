package com.tmdb.app.data.people

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tmdb.app.data.PeopleApi
import com.tmdb.app.data.response.ApiPeople
import timber.log.Timber

class PeoplePagingSource(
    private val personApi: PeopleApi,
) : PagingSource<Int, ApiPeople>() {
    override fun getRefreshKey(state: PagingState<Int, ApiPeople>): Int? {
        if (state.anchorPosition != null) {
            val closestPage = state.closestPageToPosition(state.anchorPosition!!)
            return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiPeople> {
        return try {
            val pageNo = params.key ?: 1
            val pagedResponse = personApi.getPopularPeoples(pageNo)
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


}