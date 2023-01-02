package com.tmdb.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.app.domain.usecase.session.IsOnBoardingShownUseCase
import com.tmdb.app.domain.usecase.session.SetonBoardingShownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val isOnBoardingShownUseCase: IsOnBoardingShownUseCase,
) :
    ViewModel() {
    var isReady = false
    val onboardingShown: Flow<Boolean> = isOnBoardingShownUseCase(Unit).map {
        isReady = true
        it.getOrNull() ?: false
    }.shareIn(scope = viewModelScope, started = SharingStarted.Eagerly, replay = 1)


}

