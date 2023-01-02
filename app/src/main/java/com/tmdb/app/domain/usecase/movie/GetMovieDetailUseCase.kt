package com.tmdb.app.domain.usecase.movie

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class  GetMovieDetailUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository
) :
    SuspendUseCase<Int, Movie>(dispatcher) {
    override suspend fun execute(input: Int): Movie {
        return movieRepository.getMovieDetail(input)
    }
}