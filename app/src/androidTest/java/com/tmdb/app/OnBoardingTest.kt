package com.tmdb.app

import android.app.Application
import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.tmdb.app.data.session.SessionRepositoryImpl
import com.tmdb.app.domain.usecase.session.IsOnBoardingShownUseCase
import com.tmdb.app.domain.usecase.session.SetonBoardingShownUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(JUnit4::class)
class OnBoardingTest {

    lateinit var instrumentationContext: Context
    val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    /*
        Ensure first time onboarding flag must be false
     */
    @Test
    fun first_time_onboarding_must_be_false() = runTest {
        val sessionRegistry = SessionRepositoryImpl(InstrumentationRegistry.getInstrumentation().context)
        val onBoardingShownUseCase = IsOnBoardingShownUseCase(sessionRegistry, testDispatcher)
        val shown: Flow<Result<Boolean>> = onBoardingShownUseCase.invoke(Unit)
        val res = shown.first().getOrNull() ?: false
        Assert.assertFalse(res)
    }

    @Test
    fun save_and_get_onboarding_flag() = runTest {
        val sessionRepository = SessionRepositoryImpl(instrumentationContext)
        val flagToSave = true
        val setOnBoardingShownUseCase = SetonBoardingShownUseCase(sessionRepository,testDispatcher)
        setOnBoardingShownUseCase.invoke(flagToSave)
        val isOnboardingShownUseCase = IsOnBoardingShownUseCase(sessionRepository,testDispatcher)
        val shown: Flow<Result<Boolean>> = isOnboardingShownUseCase(Unit)
        val fromDisk = shown.first().getOrNull() ?: false
        Assert.assertEquals(fromDisk, flagToSave)
    }

}