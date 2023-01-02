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
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.ui.navigateToMovieDetail
import timber.log.Timber

@Composable
fun MovieListScreen(
    navController: NavController,
    movieViewModel: MovieViewModel = hiltViewModel()
) {
    val trendingMovies: LazyPagingItems<Movie> =
        movieViewModel.trendingMovies.collectAsLazyPagingItems()

       val nowPlayingMovies: LazyPagingItems<Movie> =
           movieViewModel.nowPlayingMovies.collectAsLazyPagingItems()
       val popularMovies: LazyPagingItems<Movie> =
           movieViewModel.popularMovies.collectAsLazyPagingItems()
       val topRatedMovies: LazyPagingItems<Movie> =
           movieViewModel.topRatedMovies.collectAsLazyPagingItems()
       val upcomingMovies: LazyPagingItems<Movie> =
           movieViewModel.upcomingMovies.collectAsLazyPagingItems()
    fun onMoreClicked(homeMovieRow: MovieLoadType) {
        Timber.d("onMoreClicked homeMovieRow: ${homeMovieRow.name}")
    }

    fun onMovieClicked(movie: Movie) {
        Timber.d("onMoreClicked movie: ${movie.title}")
        navController.navigateToMovieDetail(movie.id)
    }
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        item(key = MovieLoadType.TRENDING) {
            MovieRowItems(MovieLoadType.TRENDING, trendingMovies, ::onMovieClicked, ::onMoreClicked)
        }
        item(key = MovieLoadType.NOW_PLAYING) {
            MovieRowItems(
                MovieLoadType.NOW_PLAYING,
                nowPlayingMovies,
                ::onMovieClicked,
                ::onMoreClicked
            )
        }
        item(key = MovieLoadType.POPULAR) {
            MovieRowItems(
                MovieLoadType.POPULAR,
                popularMovies,
                ::onMovieClicked,
                ::onMoreClicked
            )
        }
        item(key = MovieLoadType.TOP_RATED) {
            MovieRowItems(
                MovieLoadType.TOP_RATED,
                topRatedMovies,
                ::onMovieClicked,
                ::onMoreClicked
            )
        }
        item(key = MovieLoadType.UPCOMING) {
            MovieRowItems(
                MovieLoadType.UPCOMING,
                upcomingMovies,
                ::onMovieClicked,
                ::onMoreClicked
            )
        }
    }

}

@Composable
private fun MovieRowItems(
    homeMovieRow: MovieLoadType,
    movies: LazyPagingItems<Movie>,
    onMovieClick: (Movie) -> Unit,
    onMoreClick: (MovieLoadType) -> Unit
) {
    Column {
        if (movies.itemCount > 1) {
            MovieRowHeader(homeMovieRow, onMoreClick)
        }
        val fullWidthModifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
        when (movies.loadState.refresh) {
            is LoadState.Error -> ErrorCard(
                modifier = fullWidthModifier,
                onRetryClick = movies::refresh
            )
            is LoadState.Loading -> LoadingCard(modifier = fullWidthModifier)
            is LoadState.NotLoading -> Unit
        }

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(movies, key = { it.id }) { movie ->
                movie?.let {
                    MediaCard(
                        mediaUrl = movie.posterPath,
                        t = it,
                        onMediaClicked = onMovieClick
                    )
                }
            }
            item {
                val movieSizeModifier = Modifier.size(width = 120.dp, height = 180.dp)
                when (movies.loadState.append) {
                    is LoadState.Error -> ErrorCard(movieSizeModifier, onRetryClick = movies::retry)
                    is LoadState.Loading -> LoadingCard(movieSizeModifier)
                    is LoadState.NotLoading -> Unit
                }
            }
        }
    }
}


@Composable
private fun MovieRowHeader(
    homeMovieRow: MovieLoadType,
    onMoreClick: (MovieLoadType) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = homeMovieRow.title)
        TextButton(onClick = { onMoreClick.invoke(homeMovieRow) }) {
            Text(text = "More")
        }
    }
}
