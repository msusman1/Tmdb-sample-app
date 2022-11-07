package com.tmdb.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.usecase.people.GetPopularPeopleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPopularPeopleUseCase: GetPopularPeopleUseCase,
) : ViewModel() {
    val popularPeoples: Flow<PagingData<People>> =
        getPopularPeopleUseCase(Unit).cachedIn(viewModelScope)

}
