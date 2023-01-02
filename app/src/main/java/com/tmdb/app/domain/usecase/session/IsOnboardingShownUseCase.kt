package com.tmdb.app.domain.usecase.session

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.FlowUseCase
import com.tmdb.app.domain.repository.session.SessionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsOnBoardingShownUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    @IoDispatcher private val displacher: CoroutineDispatcher
) : FlowUseCase<Unit, Boolean>(displacher) {
    override fun execute(input: Unit): Flow<Boolean> {
        return sessionRepository.isOnBoardingShown()
    }

}
