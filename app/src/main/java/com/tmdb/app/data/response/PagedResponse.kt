package com.tmdb.app.data.response

import com.google.gson.annotations.SerializedName

data class PagedResponse<T>(
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("page")
    val pageNumber: Int,  //start from 1
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    )