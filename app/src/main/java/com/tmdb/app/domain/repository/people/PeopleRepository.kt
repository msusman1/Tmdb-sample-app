package com.tmdb.app.domain.repository.people

import androidx.paging.PagingData
import com.tmdb.app.domain.entity.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPopular(): Flow<PagingData<People>>
    suspend fun getPersonDetail(personId: Int): People
}