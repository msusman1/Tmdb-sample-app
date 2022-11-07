package com.tmdb.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomChip(label: String) {
    Box(modifier = Modifier.padding(4.dp)) {
        Text(
            label,
            modifier = Modifier
                .background(
                    color = androidx.compose.ui.graphics.Color.LightGray,
                    shape = CircleShape
                )
                .padding(vertical = 4.dp, horizontal = 6.dp)
        )
    }
}