package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tmdb.app.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  HomeScreen(parentNavController:NavController) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TMDBAppBar()
        },
        bottomBar = {
            TmdbBottomBar { screen ->
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.MovieList.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.MovieList.route) {
                MovieListScreen(navController=parentNavController)
            }
            composable(Screen.Home.TVList.route) {
                TvListScreen(navController=parentNavController)
            }
            composable(Screen.Home.PeopleList.route) {
                PeopleListScreen(navController=parentNavController)
            }
        }
    }

}
 
