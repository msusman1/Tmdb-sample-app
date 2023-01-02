package com.tmdb.app.domain.entity

data class Episode(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop: String,
    val runtime: Int
)