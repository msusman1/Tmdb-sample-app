package com.tmdb.app

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tmdb.app.ui.Screen
import com.tmdb.app.ui.seasonDetail.SeasonDetailScreen
import com.tmdb.app.ui.home.HomeScreen
import com.tmdb.app.ui.home.MovieListScreen
import com.tmdb.app.ui.home.PeopleListScreen
import com.tmdb.app.ui.home.TvListScreen
import com.tmdb.app.ui.movieDetail.MovieDetailScreen
import com.tmdb.app.ui.peopleDetail.PeopleDetailScreen
import com.tmdb.app.ui.tvDetail.TvDetailScreen


@Composable
fun TmdbNavGraph(
    startDestination: Screen = Screen.Home.MovieList,
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = startDestination.route) {
        homeScreen(navController = navController)
        peopleDetailScreen(navController = navController)
        movieDetailScreen(navController = navController)
        tvDetailScreen(navController = navController)
        seasonDetailScreen(navController = navController)
    }
}

fun NavGraphBuilder.peopleDetailScreen(navController: NavController) {
    composable(
        route = Screen.PeopleDetail.route,
        arguments = Screen.PeopleDetail.buildArguments()
    ) {
        PeopleDetailScreen(navController)
    }
}

fun NavGraphBuilder.movieDetailScreen(navController: NavController) {
    composable(
        route = Screen.MovieDetail.route,
        arguments = Screen.MovieDetail.buildArguments()
    ) {
        MovieDetailScreen(navController)
    }
}

fun NavGraphBuilder.tvDetailScreen(navController: NavController) {
    composable(
        route = Screen.TVDetail.route,
        arguments = Screen.TVDetail.buildArguments()
    ) {
        TvDetailScreen(navController)
    }
}

fun NavGraphBuilder.seasonDetailScreen(navController: NavController) {
    composable(
        route = Screen.SeasonDetail.route,
        arguments = Screen.SeasonDetail.buildArguments()
    ) {
        SeasonDetailScreen(navController)
    }
}

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable(route = Screen.Home.MovieList.route) {
        HomeScreen(startDestination = Screen.Home.TVList.route) {
            composable(Screen.Home.MovieList.route) {
                MovieListScreen(navController = navController)
            }
            composable(Screen.Home.TVList.route) {
                TvListScreen(navController = navController)
            }
            composable(Screen.Home.PeopleList.route) {
                PeopleListScreen(navController = navController)
            }
        }

    }
}
