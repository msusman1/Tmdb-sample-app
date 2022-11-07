package com.tmdb.app.ui

import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tmdb.app.Screen
import com.tmdb.app.data.response.MediaType
import com.tmdb.app.domain.entity.Movie


fun NavController.navigateToHomeMovies() {
    this.navigate(Screen.Home.MovieList.route) {
        popUpTo(Screen.Onboarding.route) { inclusive = true }
    }
}

fun NavController.navigateToPeopleDetail(peopleId: Int) {
    this.navigate("PeopleDetail/$peopleId")
}

fun NavController.navigateToMediaDetail(movie: Movie) {
    if (movie.mediaType == MediaType.MOVIE) {
        navigateToMovieDetail(movie.id)
    } else {
        navigateToTVDetail(movie.id)
    }
}

/*fun NavController.navigateToUrl(url: String) {
    navigate(Uri.parse(url))
}*/

fun NavController.navigateToMovieDetail(movieId: Int) {
    this.navigate("MovieDetail/$movieId")
}

fun NavController.navigateToTVDetail(movieId: Int) {
    this.navigate("MovieDetail/$movieId")
}
