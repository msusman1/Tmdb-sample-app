package com.tmdb.app.data

import android.content.Context
import com.tmdb.app.TMDBApp
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    val apiKey = "b6552732a14408f8ce46ed1b1920236e"
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val context: Context = TMDBApp.instance.applicationContext
        when (original.url.encodedPath) {
           /* "/3/trending/movie/day" -> {
                val getPeople: InputStream = context.assets.open("get_trending_movies.json")
                val respStr = InputStreamReader(getPeople).readText()
                return respStr.buildResponse(original)
            }
            "/3/person/popular" -> {
                val getPeople: InputStream = context.assets.open("get_people.json")
                val respStr = InputStreamReader(getPeople).readText()
                return respStr.buildResponse(original)
            }
            "/3/trending/tv/day" -> {
                val trendingTvs: InputStream = context.assets.open("get_trending_tvs.json")
                val respStr = InputStreamReader(trendingTvs).readText()
                return respStr.buildResponse(original)
            }
            "/3/person/976" -> {
                val getPeopleDetail = context.assets.open("get_people_detail.json")
                val respStr = InputStreamReader(getPeopleDetail).readText()
                return respStr.buildResponse(original)
            }
            "/3/movie/550" -> {
                val movieDetail = context.assets.open("get_movie_detail.json")
                val respStr = InputStreamReader(movieDetail).readText()
                return respStr.buildResponse(original)
            }
            "/3/tv/90669" -> {
                val tvDetail = context.assets.open("get_tv_detail.json")
                val respStr = InputStreamReader(tvDetail).readText()
                return respStr.buildResponse(original)
            }
            "/3/tv/90669/season/1" -> {
                val seasonDetail = context.assets.open("get_season_detail.json")
                val respStr = InputStreamReader(seasonDetail).readText()
                return respStr.buildResponse(original)
            }*/
            else -> {
                val originalHttpUrl = original.url
                val httpUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build()
                val requestBuilder = original.newBuilder().url(httpUrl)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }

    }

    fun String.buildResponse(request: Request): Response {
        return Response.Builder()
            .code(200)
            .request(request)
            .protocol(Protocol.HTTP_2)
            .message("responseString")
            .body(
                this
                    .toByteArray()
                    .toResponseBody(
                        "application/json".toMediaTypeOrNull()
                    )
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}

