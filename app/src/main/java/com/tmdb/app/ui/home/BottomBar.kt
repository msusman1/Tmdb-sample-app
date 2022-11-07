package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tmdb.app.R
import com.tmdb.app.Screen

@Composable
@Preview()
fun PreviewBar() {
    TmdbBottomBar { }
}

@Composable
fun TestBottomBar(clb: (Screen) -> Unit) {

    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Songs", "Artists", "Playlists")
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(Icons.Filled.Favorite, contentDescription = null) },
                label = { Text(item) },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
fun TmdbBottomBar(clb: (Screen) -> Unit) {
    val selectedIndex = remember { mutableStateOf(1) }
    NavigationBar {
        NavigationBarItem(
            icon = {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = null)
            },
            label = { Text(text = "Movie") },
            selected = selectedIndex.value == 1,
            onClick = {
                selectedIndex.value = 1
                clb.invoke(Screen.Home.MovieList)
            }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            label = { Text(text = "TV") },
            selected = selectedIndex.value == 2,
            onClick = {
                selectedIndex.value = 2
                clb.invoke(Screen.Home.TVList)
            }
        )
        NavigationBarItem(
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            label = { Text(text = "People") },
            selected = selectedIndex.value == 3,
            onClick = {
                selectedIndex.value = 3
                clb.invoke(Screen.Home.PeopleList)
            }
        )
    }
}



