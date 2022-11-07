package com.tmdb.app.data

import com.tmdb.app.data.response.ApiPeople
import com.tmdb.app.data.response.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface PeopleApi {


    @GET("person/popular")
    suspend fun getPopularPeoples(@Query("page") pageNo: Int): PagedResponse<ApiPeople>

    @GET("person/{personId}")
    suspend fun getPersonDetail(
        @Path("personId") personId: Int,
        @Query("append_to_response") appendToResponse: String = "videos,images,combined_credits,known_for"
    ): ApiPeople


}
