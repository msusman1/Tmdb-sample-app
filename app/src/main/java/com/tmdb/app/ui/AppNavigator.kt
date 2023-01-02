package com.tmdb.app.ui

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.tmdb.app.data.response.MediaType
import com.tmdb.app.domain.entity.Movie


sealed class Screen(open val route: String) {
    sealed class Home(route: String) : Screen(route) {
        object MovieList : Home("MovieList")
        object TVList : Home("TVList")
        object PeopleList : Home("PeopleList")
    }

    object Search : Screen("Search")
    object MovieDetail : Screen("MovieDetail/{movieId}") {
        fun createRoute(movieId: Int): String {
            return this.route.replace("{movieId}", movieId.toString())
        }

        fun buildArguments(): List<NamedNavArgument> {
            return listOf(navArgument("movieId") { this.type = NavType.IntType })
        }

        fun SavedStateHandle.parseMovieDetailArguments(): Args {
            return Args(this["movieId"] ?: -1)
        }

        data class Args(val movieId: Int)
    }

    object TVDetail : Screen("TvDetail/{tvId}") {
        fun createRoute(tvId: Int): String {
            return this.route.replace("{tvId}", tvId.toString())
        }

        fun buildArguments(): List<NamedNavArgument> {
            return listOf(navArgument("tvId") { this.type = NavType.IntType })
        }

        fun SavedStateHandle.parseTvDetailArguments(): Args {
            return Args(this["tvId"] ?: -1)
        }

        data class Args(val tvId: Int)
    }


    object PeopleDetail : Screen("PeopleDetail/{peopleId}") {
        fun createRoute(peopleId: Int): String {
            return this.route.replace("{peopleId}", peopleId.toString())
        }

        fun buildArguments(): List<NamedNavArgument> {
            return listOf(navArgument("peopleId") { this.type = NavType.IntType })
        }

        fun SavedStateHandle.parsePeopleDetailArguments(): Args {
            return Args(this["peopleId"] ?: -1)
        }

        data class Args(val peopleId: Int)
    }

    object SeasonDetail : Screen("SeasonDetail/{tvId}/{seasonNumber}") {
        fun createRoute(tvId: Int, seasonNumber: Int): String {
            return this.route.replace("{tvId}", tvId.toString())
                .replace("{seasonNumber}", seasonNumber.toString())

        }

        fun buildArguments(): List<NamedNavArgument> {
            return listOf(navArgument("tvId") { this.type = NavType.IntType },
                navArgument("seasonNumber") { this.type = NavType.IntType })
        }

        fun SavedStateHandle.parseSeasonDetailArguments(): Args {
            return Args(this["tvId"] ?: -1, this["seasonNumber"] ?: -1)
        }

        data class Args(val tvId: Int, val seasonNumber: Int)
    }
}


fun NavController.navigateToPeopleDetail(peopleId: Int) {
    this.navigate(Screen.PeopleDetail.createRoute(peopleId))
}

fun NavController.navigateToMediaDetail(movie: Movie) {
    if (movie.mediaType == MediaType.MOVIE) {
        navigateToMovieDetail(movie.id)
    } else {
        navigateToTVDetail(movie.id)
    }
}

fun NavController.navigateToSeasonDetail(tvId: Int, seasonNumber: Int) {
    this.navigate(Screen.SeasonDetail.createRoute(tvId, seasonNumber))
}

fun NavController.navigateToTVDetail(tvId: Int) {
    this.navigate(Screen.TVDetail.createRoute(tvId))
}

fun NavController.navigateToMovieDetail(movieId: Int) {
    this.navigate(Screen.MovieDetail.createRoute(movieId))
}

fun NavController.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    context.startActivity(intent)
}
