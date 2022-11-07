package com.tmdb.app.domain.entity

import com.google.gson.annotations.SerializedName

data class TV(
    val id: Int,
    val title: String,
    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)