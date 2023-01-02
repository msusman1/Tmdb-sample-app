package com.tmdb.app.domain.usecase.people


import androidx.paging.PagingData
import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.PaginatedFlowUseCase
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.repository.people.PeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val peopleRepository: PeopleRepository
) : PaginatedFlowUseCase<Unit, People>(dispatcher) {
    override fun execute(input: Unit): Flow<PagingData<People>> {
        return peopleRepository.getPopular()
    }
}