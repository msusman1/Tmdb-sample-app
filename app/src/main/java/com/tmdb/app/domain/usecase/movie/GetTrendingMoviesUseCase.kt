package com.tmdb.app.domain.usecase.movie


import androidx.paging.PagingData
import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.PaginatedFlowUseCase
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTrendingMoviesUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val movieRepository: MovieRepository
) : PaginatedFlowUseCase<Unit, Movie>(dispatcher) {
    override fun execute(input: Unit): Flow<PagingData<Movie>> {
        return movieRepository.getTrending()
    }
}