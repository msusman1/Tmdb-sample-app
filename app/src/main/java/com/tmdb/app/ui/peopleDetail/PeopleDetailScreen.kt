package com.tmdb.app.ui.peopleDetail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.tmdb.app.R
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.entity.People
import com.tmdb.app.ui.components.*
import com.tmdb.app.ui.home.*
import com.tmdb.app.ui.navigateToMediaDetail
import com.tmdb.app.ui.openUrl


@Composable
@Preview(showBackground = true, showSystemUi = false, heightDp = 2000)
fun PeopleDetailScreenPreview() {
    PeopleDetailLayout(People.getTest(), {}, {}, {})
}

@OptIn(ExperimentalPagerApi::class)
fun PagerState.getNextPage(): Int {
    return if (pageCount == 0 || currentPage == this.pageCount - 1) 0
    else currentPage + 1
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun PeopleDetailLayout(
    people: People, onClose: () -> Unit,
    onMovieClick: (Movie) -> Unit,
    onOpenUrl: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column {
        CloseableToolbar(people.name, onCloseClick = onClose)
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Row {
                ImagerSlider(
                    images = people.images,
                    placeholder = R.drawable.profile_placeholder,
                    modifier = Modifier.weight(0.5f),
                    cornerRadius = 8.dp
                )
                Spacer(modifier = Modifier.size(16.dp))
                Column(modifier = Modifier.weight(0.6f)) {
                    Text(text = "Known As", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = people.knownForDepartment,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(text = "Gender", style = MaterialTheme.typography.titleMedium)
                    Text(
                        text = people.gender.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.size(6.dp))
                    Text(text = "Known Credits", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.size(4.dp))
                    Badge {
                        Text(
                            text = people.popularity.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Row {
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(text = "Date of Birth", style = MaterialTheme.typography.titleMedium)
                    Text(text = people.birthday, style = MaterialTheme.typography.bodyMedium)
                }
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(text = "Place Of Birth", style = MaterialTheme.typography.titleMedium)
                    Text(text = people.placeOfBirth, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            Text(text = "Biography", style = MaterialTheme.typography.titleMedium)
            Text(text = people.biography, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = "Home page", style = MaterialTheme.typography.titleMedium)
            Text(
                text = people.homepage,
                modifier = Modifier.clickable { onOpenUrl(people.homepage) },
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "Also Known As", style = MaterialTheme.typography.titleMedium)
            FlowRow {
                people.alsoKnownAs.forEach { known ->
                    CustomChip(label = known)
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Cast",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(people.cast, key = { "${it.id}_cast" }) { movie ->
                    MediaCard(
                        mediaUrl = movie.posterPath,
                        t = movie,
                        onMediaClicked = onMovieClick
                    )
                }
            }
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Crew",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(4.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(people.crew, key = { "${it.id}_crew" }) { movie ->
                    MediaCard(
                        mediaUrl = movie.posterPath,
                        t = movie,
                        onMediaClicked = onMovieClick
                    )
                }
            }
        }
    }
}

@Composable
fun PeopleDetailScreen(
    navController: NavController,
    peopleDetailViewModel: PeopleDetailViewModel = hiltViewModel()
) {

    val uiState =
        peopleDetailViewModel.uiState.collectAsState(initial = PeopleDetailViewModel.UIState.Loading).value
    when (uiState) {
        PeopleDetailViewModel.UIState.Loading -> FullScreenProgressLayout()
        is PeopleDetailViewModel.UIState.Success -> PeopleDetailLayout(
            people = uiState.people,
            onClose = navController::navigateUp,
            onMovieClick = navController::navigateToMediaDetail,
            onOpenUrl = navController::openUrl

        )
        is PeopleDetailViewModel.UIState.Error -> FullScreenErrorLayout(
            msg = uiState.msg,
            onRetry = peopleDetailViewModel::getPersonDetail
        )
    }
}




