package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.app.domain.entity.People
import com.tmdb.app.ui.navigateToPeopleDetail
import timber.log.Timber

@Composable
fun PeopleListScreen(
    peopleViewModel: PeopleViewModel = hiltViewModel(),
    navController: NavController
) {
    val peoples = peopleViewModel.popularPeoples.collectAsLazyPagingItems()
    fun onPeopleClicked(people: People) {
        Timber.d("onPeopleClicked tv: ${people.name}")
        navController.navigateToPeopleDetail(peopleId = people.id,)
    }
    Box {
        val fullWidthModifier = Modifier
            .fillMaxSize()
        when (peoples.loadState.refresh) {
            is LoadState.Error -> ErrorCard(
                modifier = fullWidthModifier,
                onRetryClick = peoples::refresh
            )
            is LoadState.Loading -> LoadingCard(modifier = fullWidthModifier)
            is LoadState.NotLoading -> Unit
        }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            columns = GridCells.Adaptive(110.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(12.dp)
        ) {
            items(peoples.itemCount) { index ->
                peoples[index]?.let { people ->
                    PeopleCard(
                        people = people,
                        onPeopleClicked = { onPeopleClicked(people) }
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                val mediaSizeModifier = Modifier.size(width = 120.dp, height = 180.dp)
                when (peoples.loadState.append) {
                    is LoadState.Error -> ErrorCard(
                        mediaSizeModifier,
                        onRetryClick = peoples::retry
                    )
                    is LoadState.Loading -> LoadingCard(mediaSizeModifier)
                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }

}