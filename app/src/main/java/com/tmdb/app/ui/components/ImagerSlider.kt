package com.tmdb.app.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.tmdb.app.R
import com.tmdb.app.ui.peopleDetail.getNextPage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagerSlider(
    images: List<String>,
    @DrawableRes placeholder: Int,
    modifier: Modifier,
    cornerRadius:Dp
) {
    val pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()
    scope.launch {
        delay(2000)
        pagerState.animateScrollToPage(pagerState.getNextPage())
    }
    HorizontalPager(
        count = images.size, state = pagerState,
        modifier = modifier
    ) { page ->
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(cornerRadius)),
            model = ImageRequest.Builder(LocalContext.current)
                .data(images[page])
                .crossfade(true)
                .error(placeholder)
                .build(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "",
        )
    }
}