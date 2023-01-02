package com.tmdb.app.ui.home

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.app.ui.Screen

@Composable
@Preview()
fun PreviewBar() {
    TmdbBottomBar { }
}

@Composable
fun TmdbBottomBar(clb: (Screen) -> Unit) {
    val selectedIndex = remember { mutableStateOf(1) }
    NavigationBar(
        modifier = Modifier.navigationBarsPadding()
    ) {
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



