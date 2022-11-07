package com.tmdb.app.domain.entity

import com.tmdb.app.data.UrlProvider
import com.tmdb.app.data.response.MediaType
import javax.inject.Inject


data class Movie(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Long,
    val genres: List<String>,
    val homepage: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<String>,
    val productionCountries: List<String>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val spokenLanguages: List<String>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val trailer: String,
    val logo: String,
    val posters: List<String>,
    val backdrops: List<String>,
    val mediaType: MediaType,
) {
    companion object {

        fun getTest(): Movie {
            val urlProvider = UrlProvider()
            return Movie(
                id = (1..1000).random(),
                mediaType = MediaType.MOVIE,
                adult = false,
                backdropPath = urlProvider.getBackdropFullUrl("/rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg"),
                budget = 63000000,
                genres = listOf("Drama", "Thriller", "Comedy"),
                homepage = "http://www.foxmovies.com/movies/fight-club",
                originalLanguage = "en",
                originalTitle = "Fight Club",
                overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion.",
                popularity = 88.86,
                posterPath = urlProvider.getPosterFullUrl("/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"),
                productionCompanies = listOf(
                    "Regency Enterprises",
                    "Fox 2000 Pictures",
                    "The Linson Company"
                ),
                productionCountries = listOf("Germany", "United States of America"),
                releaseDate = "1999-10-15",
                revenue = 100853753,
                runtime = 139,
                spokenLanguages = listOf("English"),
                status = "Released",
                tagline = "Mischief. Mayhem. Soap.",
                title = "Fight Club",
                video = false,
                voteAverage = 8.427,
                voteCount = 25106,
                trailer = "https://www.youtube.com/watch?v=BdJKm16Co6M",
                logo = "",
                posters = listOf(
                    urlProvider.getPosterFullUrl("/r3pPehX4ik8NLYPpbDRAh0YRtMb.jpg"),
                    urlProvider.getPosterFullUrl("/pB8BM7pdSp6B6Ih7QZ4DrQ3PmJK.jpg"),
                    urlProvider.getPosterFullUrl("/66RvLrRJTm4J8l3uHXWF09AICol.jpg"),
                    urlProvider.getPosterFullUrl("/a26cQPRhJPX6GbWfQbvZdrrp9j9.jpg"),
                ),
                backdrops = listOf(
                    urlProvider.getBackdropFullUrl("/rr7E0NoGKxvbkb89eR1GwfoYjpA.jpg"),
                    urlProvider.getBackdropFullUrl("/c6OLXfKAk5BKeR6broC8pYiCquX.jpg"),
                    urlProvider.getBackdropFullUrl("/hZkgoQYus5vegHoetLkCJzb17zJ.jpg"),
                    urlProvider.getBackdropFullUrl("/ruJPyRrHYPS071XzVGPX3peYi0x.jpg"),
                ),
            )


        }
    }
}


data class Video(
    val iso6391: String,
    val iso31661: String,
    val name: String,
    val key: String,
    val site: String,
    val size: Int,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
    val id: String
)


data class SpokenLanguages(
    val englishName: String,
    val iso6391: String,
    val name: String

)


data class Backdrops(

    val aspectRatio: Double,
    val height: Int,
    val iso6391: String,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int

)

data class Logos(

    val aspectRatio: Double,
    val height: Int,
    val iso6391: String,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int

)

data class Posters(

    val aspectRatio: Double,
    val height: Int,
    val iso6391: String,
    val filePath: String,
    val voteAverage: Double,
    val voteCount: Int,
    val width: Int

)

data class Images(

    val backdrops: List<Backdrops> = arrayListOf(),
    val logos: List<Logos> = arrayListOf(),
    val posters: List<Posters> = arrayListOf()

)

data class ProductionCompanies(

    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String

)

data class ProductionCountries(

    val iso31661: String,
    val name: String

)


data class Genres(

    val id: Int,
    val name: String

)