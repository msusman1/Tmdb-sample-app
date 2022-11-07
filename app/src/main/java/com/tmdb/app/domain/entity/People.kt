package com.tmdb.app.domain.entity

import java.util.*

enum class Gender(value: Int) {
    FEMALE(1), MALE(2), OTHER(3);

    override fun toString(): String {
        return this.name.lowercase()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ENGLISH) else it.toString() }
    }
}

data class People(
    val id: Int,
    val name: String,
    val adult: Boolean,
    val biography: String,
    val gender: Gender,
    val homepage: String,
    val knownFor: List<PeopleKnownFor>,
    val knownForDepartment: String,
    val alsoKnownAs: List<String>,
    val popularity: Double,
    val profileUrl: String,
    val birthday: String,
    val deathday: String,
    val placeOfBirth: String,
    val images: List<String>,
    val cast: List<Movie>,
    val crew: List<Movie>,
) {
    companion object {
        fun getTest(): People {
            val people = People(
                id = 976,
                name = "Jason Statham",
                adult = false,
                biography = "Jason Statham is an English actor and martial artist, known for his roles in the Guy Ritchie crime films Lock, Stock and Two Smoking Barrels; Revolver; and Snatch. Statham appeared in supporting roles in several American films, such as The Italian Job, as well as playing the lead role in The Transporter, Crank, The Bank Job, War (opposite martial arts star Jet Li), and Death Race. Statham solidified his status as an action hero by appearing alongside established action film actors Sylvester Stallone, Arnold Schwarzenegger, Bruce Willis, Jet Li and Dolph Lundgren in The Expendables. He normally performs his own fight scenes and stunts.",
                gender = Gender.MALE,
                homepage = "https://www.themoviedb.org/person/976-jason-statham?language=en-US",
                knownFor = emptyList(),
                alsoKnownAs = listOf(
                    "Джейсон Стейтем",
                    "Джейсон Стэйтем",
                    "جيسون ستاثام",
                    "ジェイソン・ステイサム",
                    "เจสัน สเตธัม",
                    "傑森·史塔森",
                    "Джейсън Стейтъм",
                    "ჯეისონ სტეტჰემი",
                    "Τζέισον Στέιθαμ",
                    "JS",
                    "杰森·斯坦森",
                    "제이슨 스타뎀"
                ),
                knownForDepartment = "Acting",
                popularity = 129.647,
                profileUrl = "/whNwkEQYWLFJA8ij0WyOOAD5xhQ.jpg",
                birthday = "1967-07-26",
                deathday = "2022-07-26",
                placeOfBirth = "Shirebrook, Derbyshire, England, UK",
                images = listOf(
                    "/whNwkEQYWLFJA8ij0WyOOAD5xhQ.jpg",
                    "/whNwkEQYWLFJA8ij0WyOOAD5xhQ.jpg",
                    "/lldeQ91GwIVff43JBrpdbAAeYWj.jpg",
                    "/dqsoVOHWyK4YfxdY0odIyZxhuDB.jpg",
                    "/dyTg1DxmBmTahRw4g0ImwseXS5H.jpg",
                    "/qrRIGfZ4ikHMMIRbBQAXjPkGPst.jpg"
                ),
                cast = listOf(Movie.getTest(), Movie.getTest(), Movie.getTest()),
                crew = listOf(Movie.getTest(), Movie.getTest(), Movie.getTest())
            )
            return people
        }
    }
}

data class PeopleKnownFor(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val mediaType: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    )