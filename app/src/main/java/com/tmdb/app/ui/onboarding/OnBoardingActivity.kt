package com.tmdb.app.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tmdb.app.MainActivity
import com.tmdb.app.ui.theme.TMDBAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {
    val viewModel by viewModels<OnBoardingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    OnBoardingScreen(onSkipClicked = ::startHomeActivity)
                }
            }
        }
    }

    fun startHomeActivity() {
        viewModel.onBoardingShown(true)
        val mIntent = Intent(this, MainActivity::class.java)
        startActivity(mIntent)
    }
}