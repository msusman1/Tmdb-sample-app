package com.tmdb.app.ui.tvDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.app.domain.entity.TV
import com.tmdb.app.domain.usecase.tv.GetTvDetailUseCase
import com.tmdb.app.ui.Screen.TVDetail.parseTvDetailArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTvDetailUseCase: GetTvDetailUseCase,
) : ViewModel() {
    val uiState: Flow<UIState> get() = _uiState
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    private val movieId: Int = savedStateHandle.parseTvDetailArguments().tvId

    init {
        getTvDetail()
    }

    fun getTvDetail() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        getTvDetailUseCase(movieId).onSuccess {
            _uiState.value = UIState.Success(it)
        }.onFailure {
            _uiState.value = UIState.Error(it.message ?: "Unknown error")
        }
    }


    sealed class UIState {
        object Loading : UIState()
        data class Error(val msg: String) : UIState()
        data class Success(val tv: TV) : UIState()

    }

}