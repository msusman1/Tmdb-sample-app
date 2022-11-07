package com.tmdb.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.app.R
import com.tmdb.app.domain.entity.People


@Composable
fun LoadingCard(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorCard(modifier: Modifier, onRetryClick: () -> Unit) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        TextButton(onClick = onRetryClick) {
            Text(text = "Retry", style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PeopleCartPreview() {
    PeopleCard(people = People.getTest(), onPeopleClicked = {})
}


@Composable
fun <T> MediaCard(
    mediaUrl: String,
    t: T, onMediaClicked: (T) -> Unit
) {
    Card(modifier = Modifier
        .clickable { onMediaClicked(t) }
        .background(
            color = MaterialTheme.colorScheme.surface,
        )
        .size(width = 120.dp, height = 180.dp), shape = RoundedCornerShape(8.dp)) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(mediaUrl)
                .crossfade(true)
                .error(R.drawable.movie_placeholder)
                .build(),
            contentDescription = "", contentScale = ContentScale.FillBounds,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleCard(
    people: People, onPeopleClicked: (People) -> Unit
) {
    ElevatedCard(modifier = Modifier
        .background(color = MaterialTheme.colorScheme.surface,)
//        .height(200.dp)
       .aspectRatio(ratio = 0.6667f ,matchHeightConstraintsFirst = true)
        ,
        shape = RoundedCornerShape(8.dp),
        onClick = { onPeopleClicked(people) })
    {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(people.profileUrl )
                    .crossfade(true)
                    .error(R.drawable.profile_placeholder)
                    .placeholder(R.drawable.profile_placeholder)
                    .build(),
                contentDescription = "", contentScale = ContentScale.FillBounds,
                modifier = Modifier.height(140.dp)
            )
            Text(text = people.name, modifier = Modifier.padding(8.dp).fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}