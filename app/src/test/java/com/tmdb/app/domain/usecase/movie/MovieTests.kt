package com.tmdb.app.domain.usecase.movie

import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import com.google.gson.Gson
import com.tmdb.app.data.MovieApi
import com.tmdb.app.data.UrlProvider
import com.tmdb.app.data.mapper.MovieMapper
import com.tmdb.app.data.movie.MovieRepositoryImpl
import com.tmdb.app.domain.entity.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader


class MovieTests {
    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.url("/")
//        mockWebServer.start()
    }

    @Test
    fun latestMovie() {
        val latestMovie = readFromFile()
        assertThat(latestMovie.id).isEqualTo(215264)
    }

    private fun readFromFile(): Movie {
        val reader =
            InputStreamReader(this.javaClass.classLoader.getResourceAsStream("get_latest_movie.json"))
        val text = reader.readText()
        reader.close()
        return Gson().fromJson(text, Movie::class.java)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getLatestMovie() = runTest {
        val latestMovie = readFromFile()
        val urlProvider = UrlProvider()
        val moviewApi = Retrofit.Builder().baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create()).build().create(MovieApi::class.java)
        mockWebServer.enqueue(MockResponse().apply {
            this.setResponseCode(200)
            this.setBody(Gson().toJson(latestMovie))
        })
        val movieRepository = MovieRepositoryImpl(moviewApi, MovieMapper(urlProvider))
        val res = kotlin.runCatching { movieRepository.getLatestMovie() }
        assertWithMessage("There is no error").that(res.exceptionOrNull()).isNull()
        assertWithMessage("Latest movie is not null").that(res.getOrNull()).isNotNull()
    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }
}