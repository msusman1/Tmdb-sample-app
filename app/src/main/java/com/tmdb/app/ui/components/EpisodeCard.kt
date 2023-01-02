package com.tmdb.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.app.R
import com.tmdb.app.domain.entity.Episode

@Composable
@Preview(showSystemUi = true)
fun EpisodeCardPreview() {
    EpisodeCard(
        episode = Episode(
            id = 12,
            title = "Episode 1",
            overview = "Winter approaches Suleyman Shah's camp. On a hunt, Ertugrul rescues prisoners from the Crusaders. Commander Titus and Ustadi Azam build a new army.",
            backdrop = "https://image.tmdb.org/t/p/w780//uHyavBWJO1hbWyjUMKL6VNdqSLY.jpg",
            runtime = 120,
        )
    )
}

@Composable
fun EpisodeCard(episode: Episode) {
    ConstraintLayout(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {   }) {
        val (imageRef, titleRef, runtimeRef, descRef) = createRefs()
          AsyncImage(
              model = ImageRequest.Builder(LocalContext.current)
                  .data(episode.backdrop)
                  .crossfade(true)
                  .placeholder(R.drawable.backdrop_placeholder)
                  .error(R.drawable.backdrop_placeholder)
                  .build(),
              contentDescription = "",
              modifier = Modifier
                  .fillMaxWidth(0.3f)
                  .constrainAs(imageRef) {
                      start.linkTo(parent.start)
                      top.linkTo(parent.top)
                  }
          )
        Text(text = episode.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(titleRef) {
                top.linkTo(parent.top)
                start.linkTo(imageRef.end)
            })
        Text(text = "${episode.runtime} mins",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(runtimeRef) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            })

        Text(
            text = episode.overview,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(0.7f).constrainAs(descRef) {
                top.linkTo(titleRef.bottom)
                start.linkTo(imageRef.end)
                end.linkTo(parent.end)
            }
        )


    }

}