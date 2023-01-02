package com.tmdb.app.domain.usecase.tv

import com.tmdb.app.di.IoDispatcher
import com.tmdb.app.domain.SuspendUseCase
import com.tmdb.app.domain.entity.Episode
import com.tmdb.app.domain.entity.Season
import com.tmdb.app.domain.repository.tv.TvRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetSeasonDetailUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val tvRepository: TvRepository
) :
    SuspendUseCase<Pair<Int,Int>, Pair<Season, List<Episode>>>(dispatcher) {
    override suspend fun execute(input: Pair<Int,Int>): Pair<Season, List<Episode>> {
        val (tvId,seasonNumber)=input
        return tvRepository.getSeasonDetail(tvId,seasonNumber)
    }
}