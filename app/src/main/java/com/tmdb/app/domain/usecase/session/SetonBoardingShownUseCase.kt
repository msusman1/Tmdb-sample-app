package com.tmdb.app.domain.usecase.session

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.repository.session.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SetonBoardingShownUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : SuspendUseCase<Boolean, Unit>(dispatcher) {
    override suspend fun execute(input: Boolean) {
        sessionRepository.setOnBoardingShown(input)
    }
}