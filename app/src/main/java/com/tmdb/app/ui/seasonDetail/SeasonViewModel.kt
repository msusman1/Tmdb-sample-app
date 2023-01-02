package com.tmdb.app.ui.seasonDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.app.domain.entity.Episode
import com.tmdb.app.domain.entity.Season
import com.tmdb.app.domain.usecase.tv.GetSeasonDetailUseCase
import com.tmdb.app.ui.Screen.SeasonDetail.parseSeasonDetailArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSeasonDetailUseCase: GetSeasonDetailUseCase
) : ViewModel() {
    val uiState: Flow<UiState> get() = _uiState
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)

    init {
        getSeasonDetail()

    }

    fun getSeasonDetail() = viewModelScope.launch {
        val (tvId, seasonNumber) = savedStateHandle.parseSeasonDetailArguments()
        _uiState.value = UiState.Loading
        getSeasonDetailUseCase(tvId to seasonNumber).onSuccess {
            _uiState.value = UiState.Success(it)
        }.onFailure {
            _uiState.value = UiState.Error(it.message ?: "Unknown error")
        }
    }


    sealed class UiState {
        object Loading : UiState()
        data class Error(val msg: String) : UiState()
        data class Success(val data: Pair<Season, List<Episode>>) : UiState()
    }
}