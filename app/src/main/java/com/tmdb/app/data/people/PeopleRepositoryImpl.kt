package com.tmdb.app.data.people

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.tmdb.app.data.PeopleApi
import com.tmdb.app.data.mapper.PeopleMapper
import com.tmdb.app.data.response.ApiPeople
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.repository.people.PeopleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val TV_PAGE_SIZE = 20

class PeopleRepositoryImpl @Inject constructor(
    private val peopleApi: PeopleApi,
    private val peopleMapper: PeopleMapper
) : PeopleRepository {


    private fun Flow<PagingData<ApiPeople>>.mapToDomain(): Flow<PagingData<People>> {
        return map { it.map { peopleMapper.mapToDomain(it) } }
    }


    override fun getPopular() = Pager(config = PagingConfig(pageSize = TV_PAGE_SIZE),
        pagingSourceFactory = { PeoplePagingSource(peopleApi) }
    ).flow.mapToDomain()


    override suspend fun getPersonDetail(personId: Int): People {
        val apiTv = peopleApi.getPersonDetail(personId)
        return peopleMapper.mapToDomain(apiTv)
    }
}