package com.tmdb.app.domain.repository.session

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun isOnBoardingShown(): Flow<Boolean>
    suspend fun setOnBoardingShown(shown: Boolean)
}