package com.tmdb.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.tmdb.app.ui.onboarding.OnBoardingActivity
import com.tmdb.app.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.onboardingShown.flowWithLifecycle(lifecycle).collect(::handleOnBoardingState)
        }
        setupPreDrawlistener()
    }


    private fun setupPreDrawlistener() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                return if (viewModel.isReady) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else {
                    false
                }
            }
        })
    }

    private fun handleOnBoardingState(onboardingShown: Boolean) {
        Timber.d("handleOnBoardingState onboardingShown:$onboardingShown")
        if (onboardingShown.not()) {
            val onBoardingIntnent = Intent(this@MainActivity, OnBoardingActivity::class.java)
            startActivity(onBoardingIntnent).also { finish() }
        } else {
            setContent {
                TMDBAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        TmdbNavGraph()
                    }
                }
            }
        }
    }


}


