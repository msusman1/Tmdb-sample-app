package com.tmdb.app.ui.movieDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.google.accompanist.pager.PagerState
import com.tmdb.app.domain.entity.Movie
import com.tmdb.app.domain.usecase.movie.GetMovieDetailUseCase
import com.tmdb.app.domain.usecase.movie.GetRelatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getRelatedMoviesUseCase: GetRelatedMoviesUseCase
) : ViewModel() {
    val uiState: Flow<UIState> get() = _uiState
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])

    val uiStateRelatedMovies: Flow<UIState> get() = _uiStateRelatedMovies
    private val _uiStateRelatedMovies = MutableStateFlow<UIState>(UIState.Loading)

    val relatedMovies: Flow<PagingData<Movie>> =
        getRelatedMoviesUseCase.invoke(movieId).cachedIn(viewModelScope)
    init {
        getMovieDetail()
    }

    fun getMovieDetail() = viewModelScope.launch {
        _uiState.value = UIState.Loading
        getMovieDetailUseCase(movieId).onSuccess {
            _uiState.value = UIState.Success(it)
        }.onFailure {
            _uiState.value = UIState.Error(it.message ?: "Unknown error")
        }
    }


    sealed class UIState {
        object Loading : UIState()
        data class Error(val msg: String) : UIState()
        data class Success(val movie: Movie) : UIState()

    }

}