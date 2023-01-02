package com.tmdb.app.ui.tvDetail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.tmdb.app.R
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.ui.components.*
import com.tmdb.app.ui.home.MediaCard
import com.tmdb.app.ui.movieDetail.langCodeToReadable
import com.tmdb.app.ui.navigateToSeasonDetail
import com.tmdb.app.ui.openUrl
import java.util.*

@Composable
fun TvDetailScreen(
    navController: NavController, tvDetailViewModel: TvDetailViewModel = hiltViewModel()
) {
    val uiState =
        tvDetailViewModel.uiState.collectAsState(initial = TvDetailViewModel.UIState.Loading).value

    when (uiState) {
        is TvDetailViewModel.UIState.Loading -> FullScreenProgressLayout()
        is TvDetailViewModel.UIState.Success -> TvDetailLayout(
            tv = uiState.tv,
            onClose = navController::navigateUp,
            onSeasonClick = navController::navigateToSeasonDetail,
            onOpenUrl = navController::openUrl,
            onPlayTrailer = navController::openUrl
        )
        is TvDetailViewModel.UIState.Error -> FullScreenErrorLayout(
            msg = uiState.msg, onRetry = tvDetailViewModel::getTvDetail
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MovieDetailPreview() {
    TvDetailLayout(tv = TV.getTest(),
        onClose = {},
        onSeasonClick = { _, _ -> },
        onOpenUrl = {},
        onPlayTrailer = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TvDetailLayout(
    tv: TV,
    onClose: () -> Unit,
    onSeasonClick: (Int, Int) -> Unit,
    onPlayTrailer: (String) -> Unit,
    onOpenUrl: (String) -> Unit,
) {

    val scrollState = rememberScrollState()
    Column {
        CloseableToolbar(title = tv.title, onClose)
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            ImagerSlider(
                images = tv.backdrops,
                placeholder = R.drawable.banner_placeholder,
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 0.dp
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (tv.trailer.isNotEmpty()) {
                        IconButton(
                            onClick = { onPlayTrailer(tv.trailer) }, modifier = Modifier.border(
                                BorderStroke(2.dp, Color.Black), shape = CircleShape
                            )
                        ) {
                            Icon(
                                Icons.Filled.PlayArrow, contentDescription = ""
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(weight = 1.0f))
                    Text(text = tv.type)
                    Spacer(modifier = Modifier.width(16.dp))
                    Badge()
                    Spacer(modifier = Modifier.width(16.dp))
                    Badge {
                        Text(
                            text = tv.popularity.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                ConstraintLayout {
                    val (top_left, top_right, bottom_left, bottom_right) = createRefs()
                    val startGuideline = createGuidelineFromStart(0.7f)
                    Column(modifier = Modifier.constrainAs(top_left) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }) {
                        Text(text = "First Air Date", style = MaterialTheme.typography.titleMedium)
                        Text(text = tv.firstAirDate, style = MaterialTheme.typography.bodyMedium)
                    }
                    Column(modifier = Modifier.constrainAs(top_right) {
                        top.linkTo(parent.top)
                        start.linkTo(startGuideline)
                    }) {
                        Text(text = "Last Air Date", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = tv.lastAirDate, style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.constrainAs(bottom_left) {
                        top.linkTo(top_left.bottom)
                        start.linkTo(parent.start)
                    }) {
                        Text(
                            text = "Original Language", style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = tv.originalLanguage.langCodeToReadable(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.constrainAs(bottom_right) {
                        top.linkTo(top_right.bottom)
                        start.linkTo(startGuideline)
                    }) {
                        Text(text = "Status", style = MaterialTheme.typography.titleMedium)
                        Text(text = tv.status, style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Overview", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = tv.overview, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow {
                    tv.genres.forEach { known ->
                        CustomChip(label = known)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Home page", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = tv.homepage,
                    modifier = Modifier.clickable { onOpenUrl(tv.homepage) },
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Seasons",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(4.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(tv.seasons, key = { it.seasonNumber }) { season ->
                        MediaCard(mediaUrl = season.poster,
                            t = season,
                            onMediaClicked = { onSeasonClick.invoke(tv.id, season.seasonNumber) })
                    }
                }
            }
        }
    }
}
