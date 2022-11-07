package com.tmdb.app.domain.usecase.people

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.repository.people.PeopleRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPersonDetailUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val peopleRepository: PeopleRepository
) :
    SuspendUseCase<Int, People>(dispatcher) {
    override suspend fun execute(input: Int): People {
        return peopleRepository.getPersonDetail(input)
    }
}