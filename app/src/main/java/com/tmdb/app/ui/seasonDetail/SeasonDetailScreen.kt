package com.tmdb.app.ui.seasonDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.app.ui.components.CloseableToolbar
import com.tmdb.app.ui.components.EpisodeCard
import com.tmdb.app.ui.components.FullScreenErrorLayout
import com.tmdb.app.ui.components.FullScreenProgressLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeasonDetailScreen(
    navController: NavController, seasonViewModel: SeasonViewModel = hiltViewModel()
) {
    val state =
        seasonViewModel.uiState.collectAsState(initial = SeasonViewModel.UiState.Loading).value
    when (state) {
        is SeasonViewModel.UiState.Error -> FullScreenErrorLayout(
            msg = state.msg, seasonViewModel::getSeasonDetail
        )
        is SeasonViewModel.UiState.Loading -> FullScreenProgressLayout()
        is SeasonViewModel.UiState.Success -> {
            val (season, episodes) = state.data
            Column {
                CloseableToolbar(
                    title = season.title,
                    onCloseClick = navController::navigateUp,
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(episodes, key = { it.id }) { episode ->
                        EpisodeCard(episode = episode)
                    }
                }
            }
        }

    }
}