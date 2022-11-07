package com.tmdb.app.ui.peopleDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.app.domain.entity.People
import com.tmdb.app.domain.usecase.people.GetPersonDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPersonDetailUseCase: GetPersonDetailUseCase
) : ViewModel() {
    val uiState: Flow<UIState> get() = _uiState
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    private val personId: Int = checkNotNull(savedStateHandle["peopleId"])

    init {
        getPersonDetail()
    }

    fun getPersonDetail() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        getPersonDetailUseCase(personId).onSuccess {
            _uiState.value = UIState.Success(it)
        }.onFailure {
            _uiState.value = UIState.Error(it.message?:"Unknown error")
        }
    }

    sealed class UIState {
        object Loading : UIState()
        data class Error(val msg: String) : UIState()
        data class Success(val people: People) : UIState()

    }

}