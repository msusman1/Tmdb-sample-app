package com.tmdb.app.ui.movieDetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.flowlayout.FlowRow
import com.tmdb.app.R
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.ui.components.*
import com.tmdb.app.ui.home.ErrorCard
import com.tmdb.app.ui.home.LoadingCard
import com.tmdb.app.ui.home.MediaCard
import com.tmdb.app.ui.navigateToMovieDetail
import com.tmdb.app.ui.openUrl
import kotlinx.coroutines.flow.flow
import java.util.*

@Composable
fun MovieDetailScreen(
    navController: NavController, movieDetailViewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState =
        movieDetailViewModel.uiState.collectAsState(initial = MovieDetailViewModel.UIState.Loading).value
    val relatedMovies: LazyPagingItems<Movie> =
        movieDetailViewModel.relatedMovies.collectAsLazyPagingItems()
    when (uiState) {
        is MovieDetailViewModel.UIState.Loading -> FullScreenProgressLayout()
        is MovieDetailViewModel.UIState.Success -> MovieDetailLayout(
            movie = uiState.movie, onClose = navController::navigateUp,
            relatedMovies = relatedMovies,
            onMovieClick = navController::navigateToMovieDetail,
            onOpenUrl = navController::openUrl,
            onPlayTrailer = navController::openUrl
        )
        is MovieDetailViewModel.UIState.Error -> FullScreenErrorLayout(
            msg = uiState.msg, onRetry = movieDetailViewModel::getMovieDetail
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MovieDetailPreview() {
    MovieDetailLayout(
        movie = Movie.getTest(),
        onClose = {},
        relatedMovies = flow<PagingData<Movie>> { }.collectAsLazyPagingItems(),
        onMovieClick = {},
        onOpenUrl = {},
        onPlayTrailer = {},
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailLayout(
    movie: Movie,
    onClose: () -> Unit,
    relatedMovies: LazyPagingItems<Movie>,
    onMovieClick: (Int) -> Unit,
    onOpenUrl: (String) -> Unit,
    onPlayTrailer: (String) -> Unit
) {

    val scrollState = rememberScrollState()
    Column {
        CloseableToolbar(title = movie.title, onClose)
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            ImagerSlider(
                images = movie.backdrops,
                placeholder = R.drawable.banner_placeholder,
                modifier = Modifier.fillMaxWidth(),
                cornerRadius = 0.dp
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (movie.trailer.isNotEmpty()) {
                        IconButton(
                            onClick = { onPlayTrailer(movie.trailer) },
                            modifier = Modifier.border(
                                BorderStroke(2.dp, Color.Black),
                                shape = CircleShape
                            )
                        ) {
                            Icon(
                                Icons.Filled.PlayArrow, contentDescription = ""
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(weight = 1.0f))
                    Text(text = movie.runtime.minutesToReadable())
                    Spacer(modifier = Modifier.width(16.dp))
                    Badge()
                    Spacer(modifier = Modifier.width(16.dp))
                    Badge {
                        Text(
                            text = movie.popularity.toString(),
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
                        Text(text = "Released Date", style = MaterialTheme.typography.titleMedium)
                        Text(text = movie.releaseDate, style = MaterialTheme.typography.bodyMedium)
                    }
                    Column(modifier = Modifier.constrainAs(top_right) {
                        top.linkTo(parent.top)
                        start.linkTo(startGuideline)
                    }) {
                        Text(text = "Language", style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = movie.originalLanguage.langCodeToReadable(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.constrainAs(bottom_left) {
                        top.linkTo(top_left.bottom)
                        start.linkTo(parent.start)
                    }) {
                        Text(text = "Budget", style = MaterialTheme.typography.titleMedium)
                        Text(text = "${movie.budget}", style = MaterialTheme.typography.bodyMedium)
                    }
                    Column(modifier = Modifier.constrainAs(bottom_right) {
                        top.linkTo(top_right.bottom)
                        start.linkTo(startGuideline)
                    }) {
                        Text(text = "Revenue", style = MaterialTheme.typography.titleMedium)
                        Text(text = "${movie.revenue}", style = MaterialTheme.typography.bodyMedium)
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Biography", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = movie.overview, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                FlowRow {
                    movie.genres.forEach { known ->
                        CustomChip(label = known)
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Home page", style = MaterialTheme.typography.titleMedium)
                Text(
                    text = movie.homepage,
                    modifier = Modifier.clickable { onOpenUrl(movie.homepage) },
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (relatedMovies.itemCount > 1) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Related",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                val fullWidthModifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                when (relatedMovies.loadState.refresh) {
                    is LoadState.Error -> ErrorCard(
                        modifier = fullWidthModifier,
                        onRetryClick = relatedMovies::refresh
                    )
                    is LoadState.Loading -> LoadingCard(modifier = fullWidthModifier)
                    is LoadState.NotLoading -> Unit
                }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(relatedMovies, key = { it.id }) { movie ->
                        movie?.let {
                            MediaCard(
                                mediaUrl = movie.posterPath,
                                t = it,
                                onMediaClicked = {onMovieClick.invoke(movie.id)}
                            )
                        }
                    }
                    item {
                        val movieSizeModifier = Modifier.size(width = 120.dp, height = 180.dp)
                        when (relatedMovies.loadState.append) {
                            is LoadState.Error -> ErrorCard(
                                movieSizeModifier,
                                onRetryClick = relatedMovies::retry
                            )
                            is LoadState.Loading -> LoadingCard(movieSizeModifier)
                            is LoadState.NotLoading -> Unit
                        }
                    }
                }

            }
        }
    }
}

fun String.langCodeToReadable(): String {
    return Locale(this).displayLanguage
}

fun Int.minutesToReadable(): String {
    val mins = this % 60
    val hours = this / 60
    return "${hours}h ${mins}m"
}