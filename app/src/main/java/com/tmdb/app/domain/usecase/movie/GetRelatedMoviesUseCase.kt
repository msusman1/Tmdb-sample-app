package com.tmdb.app.domain.usecase.movie

import androidx.paging.PagingData
import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.PaginatedFlowUseCase
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetRelatedMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : PaginatedFlowUseCase<Int, Movie>(dispatcher) {
    override fun execute(input: Int): Flow<PagingData<Movie>> {
        return movieRepository.getRelated(input)
    }
}