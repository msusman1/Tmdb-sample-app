package com.tmdb.app.domain.usecase.tv

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.repository.movie.MovieRepository
import com.tmdb.app.domain.repository.people.PeopleRepository
import com.tmdb.app.domain.repository.tv.TvRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetTvDetailUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val tvRepository: TvRepository
) : SuspendUseCase<Int, TV>(dispatcher) {
    override suspend fun execute(input: Int): TV {
        return tvRepository.getTvDetail(input)
    }
}