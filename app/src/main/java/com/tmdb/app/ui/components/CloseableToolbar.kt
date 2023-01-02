package com.tmdb.app.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdb.app.ui.home.*

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CloseableToolbar(
    title: String,
    onCloseClick: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onCloseClick() },
                contentDescription = "Close"
            )
        } )
}
