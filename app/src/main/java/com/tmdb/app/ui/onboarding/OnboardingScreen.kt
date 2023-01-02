package com.tmdb.app.ui.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.tmdb.app.ui.theme.TMDBAppTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    onSkipClicked: () -> Unit
) {
    val pagerState = rememberPagerState(initialPage = 0)

    Column {
        ElevatedButton(
            onClick = { onSkipClicked() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Skip")
        }

        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            PageUI(page = onboardPages[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            activeColor = MaterialTheme.colorScheme.primary
        )

        AnimatedVisibility(visible = pagerState.currentPage == 2) {
            OutlinedButton(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), onClick = onSkipClicked,
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = "Get Started")
            }
        }
        Spacer(modifier = Modifier.size(8.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TMDBAppTheme {
        OnBoardingScreen {}
    }
}

@Composable
fun PageUI(page: Page) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(page.image),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(16.dp),
            text = page.description,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

    }
}

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val onboardPages = listOf(
    Page(
        "Movies",
        "Watch your favourite movies, Search movies by different criteria",
        com.tmdb.app.R.drawable.nav_image_movie
    ),
    Page(
        "TV Shows",
        "Explore TV shows, Seasons , Episode detail.",
        com.tmdb.app.R.drawable.nav_image_tv
    ),
    Page(
        "People",
        "Search/view people and cast from 2+ millions characters.",
        com.tmdb.app.R.drawable.nav_image_actor
    )
)