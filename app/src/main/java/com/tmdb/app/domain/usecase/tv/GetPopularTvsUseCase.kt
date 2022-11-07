package com.tmdb.app.domain.usecase.tv


import androidx.paging.PagingData
import com.tmdb.app.di.DefaultDispatcher
import com.tmdb.app.domain.PaginatedFlowUseCase
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.repository.tv.TvRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTvsUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val tvRepository: TvRepository
) : PaginatedFlowUseCase<Unit, TV>(dispatcher) {
    override fun execute(input: Unit): Flow<PagingData<TV>> {
        return tvRepository.getPopular()
    }
}