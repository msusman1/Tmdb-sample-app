package com.tmdb.app.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class SuspendUseCase<in Input, out Output>(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(input: Input): Result<Output> {
        return try {
            withContext(dispatcher) {
                val result = execute(input)
                Result.success(result)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    protected abstract suspend fun execute(input: Input): Output
}