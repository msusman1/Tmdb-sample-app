package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.ui.navigateToTVDetail
import timber.log.Timber

@Composable
fun TvListScreen(tvViewModel: TvViewModel = hiltViewModel(), navController: NavController) {

    val trendingTvs: LazyPagingItems<TV> =
        tvViewModel.trendingTvs.collectAsLazyPagingItems()

    val popularTvs: LazyPagingItems<TV> =
        tvViewModel.popularTvs.collectAsLazyPagingItems()
    val airingTodayTvs: LazyPagingItems<TV> =
        tvViewModel.airingTodayTvs.collectAsLazyPagingItems()
    val onTheAirTvs: LazyPagingItems<TV> =
        tvViewModel.onTheAirTvs.collectAsLazyPagingItems()
    val topRatedTvs: LazyPagingItems<TV> =
        tvViewModel.topRatedTvs.collectAsLazyPagingItems()

    fun onMoreClicked(tvLoadType: TVLoadType) {
        Timber.d("onMoreClicked tvLoadType: ${tvLoadType.name}")
    }

    fun onTvClicked(tv: TV) {
        navController.navigateToTVDetail(tv.id)
        Timber.d("onTvClicked tv: ${tv.title}")

    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item(key = TVLoadType.TRENDING) {
            TvRowItems(TVLoadType.TRENDING, trendingTvs, ::onTvClicked, ::onMoreClicked)
        }
        item(key = TVLoadType.POPULAR) {
            TvRowItems(
                TVLoadType.POPULAR,
                popularTvs,
                ::onTvClicked,
                ::onMoreClicked
            )
        }
        item(key = TVLoadType.AIRING_TODAY) {
            TvRowItems(
                TVLoadType.AIRING_TODAY,
                airingTodayTvs,
                ::onTvClicked,
                ::onMoreClicked
            )
        }
        item(key = TVLoadType.ON_THE_AIR) {
            TvRowItems(
                TVLoadType.ON_THE_AIR,
                onTheAirTvs,
                ::onTvClicked,
                ::onMoreClicked
            )
        }
        item(key = TVLoadType.TOP_RATED) {
            TvRowItems(
                TVLoadType.TOP_RATED,
                topRatedTvs,
                ::onTvClicked,
                ::onMoreClicked
            )
        }
    }

}


@Composable
private fun TvRowItems(
    tvLoadType: TVLoadType,
    tvs: LazyPagingItems<TV>,
    onTvClick: (TV) -> Unit,
    onMoreClick: (TVLoadType) -> Unit
) {
    Column {
        if (tvs.itemCount > 1) {
            TVRowHeader(tvLoadType, onMoreClick)
        }
        val fullWidthModifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
        when (tvs.loadState.refresh) {
            is LoadState.Error -> ErrorCard(
                modifier = fullWidthModifier,
                onRetryClick = tvs::refresh
            )
            is LoadState.Loading -> LoadingCard(modifier = fullWidthModifier)
            is LoadState.NotLoading -> Unit
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(tvs, key = { it.id }) { tv ->
                tv?.let {
                    MediaCard(
                        mediaUrl = tv.poster,
                        t = it,
                        onMediaClicked = onTvClick
                    )
                }
            }
            item {
                val mediaSizeModifier = Modifier.size(width = 120.dp, height = 180.dp)
                when (tvs.loadState.append) {
                    is LoadState.Error -> ErrorCard(mediaSizeModifier, onRetryClick = tvs::retry)
                    is LoadState.Loading -> LoadingCard(mediaSizeModifier)
                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}


@Composable
private fun TVRowHeader(
    tvLoadType: TVLoadType,
    onMoreClick: (TVLoadType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = tvLoadType.title)
        TextButton(onClick = { onMoreClick.invoke(tvLoadType) }) {
            Text(text = "More")
        }
    }
}
