package com.tmdb.app

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tmdb.app.ui.home.HomeScreen
import com.tmdb.app.ui.movieDetail.MovieDetailScreen
import com.tmdb.app.ui.navigateToHomeMovies
import com.tmdb.app.ui.onboarding.OnboardingScreen
import com.tmdb.app.ui.peopleDetail.PeopleDetailScreen

sealed class Screen(open val route: String) {
    object Onboarding : Screen("Onboarding")
    sealed class Home(route: String) : Screen(route) {
        object MovieList : Home("MovieList")
        object TVList : Home("TVList")
        object PeopleList : Home("PeopleList")
    }

    object Search : Screen("Search")
    object Player : Screen("Player")
    object MovieDetail : Screen("MovieDetail/{movieId}")
    object TVDetail : Screen("TVDetail/{tvId}")
    object PeopleDetail : Screen("PeopleDetail/{peopleId}")
}


@Composable
fun TmdbNavGraph(
    startDestination: Screen = Screen.Home.MovieList,
    navController: NavHostController = rememberNavController()
) {

    NavHost(navController = navController, startDestination = startDestination.route) {
        onBoardingScreen(onSkipClicked = navController::navigateToHomeMovies)
        homeScreen(navController = navController)
        peopleDetailScreen(navController = navController)
        movieDetailScreen(navController = navController)
    }
}

fun NavGraphBuilder.peopleDetailScreen(navController:NavController) {
    composable(
        route = Screen.PeopleDetail.route,
        arguments = listOf(navArgument("peopleId") { this.type = NavType.IntType })
    ) {
        PeopleDetailScreen(navController)
    }
}
fun NavGraphBuilder.movieDetailScreen(navController:NavController) {
    composable(
        route = Screen.MovieDetail.route,
        arguments = listOf(navArgument("movieId") { this.type = NavType.IntType })
    ) {
        MovieDetailScreen(navController)
    }
}
fun NavGraphBuilder.homeScreen(navController:NavController) {
    composable(route = Screen.Home.MovieList.route) {
        HomeScreen(navController)
    }
}
fun NavGraphBuilder.onBoardingScreen(onSkipClicked: () -> Unit) {
    composable(route = Screen.Onboarding.route) {
        OnboardingScreen(onSkipClicked = onSkipClicked, onGettingStartedClick = onSkipClicked)
    }
}