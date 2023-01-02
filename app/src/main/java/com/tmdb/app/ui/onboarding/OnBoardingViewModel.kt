package com.tmdb.app.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.app.domain.usecase.session.SetonBoardingShownUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setOnBoardingShownUseCase: SetonBoardingShownUseCase
) : ViewModel() {
    fun onBoardingShown(shown: Boolean) = viewModelScope.launch {
        setOnBoardingShownUseCase.invoke(shown)
    }
}