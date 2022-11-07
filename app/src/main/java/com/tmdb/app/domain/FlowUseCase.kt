package com.tmdb.app.domain

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowUseCase<in Input, out Output>(
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(input: Input): Flow<Result<Output>> {
        val result: Flow<Result<Output>> = execute(input).map {
            Result.success(it)
        }.catch { emit(Result.failure(Exception(it))) }
            .flowOn(dispatcher)
        return result
    }

    protected abstract fun execute(input: Input): Flow<Output>
}

abstract class PaginatedFlowUseCase<in Input,  Output:Any>(
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(input: Input): Flow<PagingData<Output>> {
        return execute(input).flowOn(dispatcher)
    }

    protected abstract fun execute(input: Input): Flow<PagingData<Output>>
}
