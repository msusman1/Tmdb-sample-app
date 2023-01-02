package com.tmdb.app.data.session

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tmdb.app.domain.repository.session.SessionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tmdb-prefs")
private val ONBOARDING_SHOWN_KEY = booleanPreferencesKey("onboarding_key")

class SessionRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    SessionRepository {
    override fun isOnBoardingShown(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[ONBOARDING_SHOWN_KEY] ?: false
        }
    }

    override suspend fun setOnBoardingShown(shown: Boolean) {
        context.dataStore.edit {
            it[ONBOARDING_SHOWN_KEY] = shown
        }
    }
}


