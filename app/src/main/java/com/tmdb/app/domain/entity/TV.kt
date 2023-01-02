package com.tmdb.app.domain.entity

import com.tmdb.app.data.UrlProvider

data class TV(
    val id: Int,
    val title: String,
    val backdrops: List<String>,
    val firstAirDate: String,
    val lastAirDate: String,
    val popularity: Double,
    val originalLanguage: String,
    val status: String,
    val genres: List<String>,
    val overview: String,
    val homepage: String,
    val poster: String,
    val trailer: String,
    val type: String,
    val seasons: List<Season>
) {
    companion object {

        fun getTest(): TV {
            val urlProvider = UrlProvider()
            return TV(
                id = (1..1000).random(),
                title = "Resurrection: Ertugrul",
                backdrops = listOf(
                    urlProvider.getBackdropFullUrl("/lVVsPeqD4sQEBfRH9ELIlUQJibl.jpg"),
                    urlProvider.getBackdropFullUrl("/zXDnRkXGzOawyR6hMGje3ENtJeF.jpg"),
                    urlProvider.getBackdropFullUrl("/oTjxxep0ManYataub20UZwtM4om.jpg"),
                    urlProvider.getBackdropFullUrl("/e65MQuI9wf4IyV9a6RkcO7yx71c.jpg"),
                ),
                firstAirDate = "2014-12-11",
                lastAirDate = "2019-05-22",
                popularity = 37.278,
                originalLanguage = "tr",
                status = "Ended",
                genres = listOf("Action & Adventure", "Drama", "War & Politics"),
                overview = "Ertuğrul Bey and the Knights Templar in the 13th century Alba and step and step with the struggle against brutal Mongols depicts the process of establishing the Ottoman principality",
                homepage = "http://www.trt1.com.tr/diziler/dirilis-ertugrul",
                poster = urlProvider.getPosterFullUrl("/tEP6IdubwPcK3nGlPb2vKYidymi.jpg"),
                trailer = "https://www.youtube.com/watch?v=c6B-EG1EafM",
                type = "Scripted",
                seasons = listOf(
                    Season(
                        title = "Season 1",
                        seasonNumber = 1,
                        poster = urlProvider.getPosterFullUrl("/mo17qMfpML0LlNPoMChDy6KZaeE.jpg")
                    ),
                    Season(
                        title = "Season 2",
                        seasonNumber = 2,
                        poster = urlProvider.getPosterFullUrl("/syIcNmRoTKzk0IcUtg56bKRCHuq.jpg")
                    ),
                    Season(
                        title = "Season 3",
                        seasonNumber = 3,
                        poster = urlProvider.getPosterFullUrl("/v8ws7c2KHJY7PiwUcKPGENrThe2.jpg")
                    ),
                    Season(
                        title = "Season 4",
                        seasonNumber = 4,
                        poster = urlProvider.getPosterFullUrl("/6vz6CCBjBxkUAh7EEV13WNSNEEh.jpg")
                    ),
                    Season(
                        title = "Season 5",
                        seasonNumber = 5,
                        poster = urlProvider.getPosterFullUrl("/7jt2SfFtaywDPwYHF9WhJSdckVO.jpg")
                    ),
                )
            )


        }
    }

}


