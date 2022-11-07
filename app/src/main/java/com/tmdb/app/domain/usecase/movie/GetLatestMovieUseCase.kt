package com.tmdb.app.domain.usecase.movie

import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.di.DefaultDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository
) : SuspendUseCase<Unit, Movie>(dispatcher) {
    override suspend fun execute(input: Unit): Movie {
        return movieRepository.getLatestMovie()
    }
}