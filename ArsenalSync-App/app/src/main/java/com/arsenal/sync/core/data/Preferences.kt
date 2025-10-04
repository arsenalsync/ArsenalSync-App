package com.arsenal.sync.core.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.arsenal.sync.core.domain.model.User
import com.arsenal.sync.core.domain.utils.ThemeOption
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Preferences @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val DATASTORE_NAME = "app_preferences"

        // DataStore instance
        private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

        // Keys for preferences
        private val SELECTED_THEME = stringPreferencesKey("selected_theme")
        private val IS_ONBOARDED = booleanPreferencesKey("is_onboarded")
        private val IS_REMEMBER = booleanPreferencesKey("is_remember")
        private val IS_VERIFIED = booleanPreferencesKey("is_verified")
        private val IS_LOCKED = booleanPreferencesKey("is_locked")
        private val RADIUS = floatPreferencesKey("radius")
        private val TIME_POLICY = intPreferencesKey("time_policy")
        private val HOURS = intPreferencesKey("hours")
        private val EMAIL = stringPreferencesKey("email")
        private val PASSWORD = stringPreferencesKey("password")
        private val FIRST_NAME = stringPreferencesKey("first_name")
    }

    // Read 'isOnboarded'
    suspend fun getIsOnboarded(): Boolean =
        context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            preferences[IS_ONBOARDED] ?: false
        }.first()

    // Set 'isOnboarded'
    suspend fun setIsOnboarded(isOnboarded: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_ONBOARDED] = isOnboarded
        }
    }

    // Clear all preferences based on isRemember
    suspend fun clearAllPreferences() {
        context.dataStore.edit {
            if (it[IS_REMEMBER] == true) {
                it[IS_ONBOARDED] = false
                it[FIRST_NAME] = ""
            } else {
                it.clear()
            }
        }
    }

    // Get user details
    fun getUser(): Flow<User> =
        context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            val email = preferences[EMAIL] ?: ""
            val firstName = preferences[FIRST_NAME] ?: ""
            val isVerified = preferences[IS_VERIFIED] ?: false

            User(
                email = email,
                firstName = firstName,
                isVerified = isVerified
            )
        }

    // Get Auth credentials
    fun getAuthCredentials(): Flow<Pair<String, String>> =
        context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            val email = preferences[EMAIL] ?: ""
            val password = preferences[PASSWORD] ?: ""
            Pair(email, password)
        }

    // Set user details
    suspend fun setUser(
        isRemember: Boolean,
        email: String,
        isVerified: Boolean,
        firstName: String,
        password: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[IS_REMEMBER] = isRemember
            preferences[EMAIL] = email
            preferences[IS_VERIFIED] = isVerified
            preferences[PASSWORD] = password
            preferences[FIRST_NAME] = firstName
        }
    }

    // Set user details
    suspend fun setUserData(
        email: String,
        isVerified: Boolean,
        firstName: String
    ) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL] = email
            preferences[IS_VERIFIED] = isVerified
            preferences[FIRST_NAME] = firstName
        }
    }

    suspend fun setTheme(themeOption: ThemeOption) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_THEME] = themeOption.displayName
        }
    }

    fun getTheme(): Flow<ThemeOption> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            val theme = preferences[SELECTED_THEME]
            when (theme) {
                ThemeOption.DARK.displayName -> ThemeOption.DARK
                ThemeOption.LIGHT.displayName -> ThemeOption.LIGHT
                else -> ThemeOption.SYSTEM
            }
        }
    }

    fun getIsLocked(): Flow<Boolean> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            preferences[IS_LOCKED] ?: false
        }
    }

    suspend fun setIsLocked(locked: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOCKED] = locked
        }
    }

    suspend fun setRadius(radius: Float) {
        context.dataStore.edit { preferences ->
            preferences[RADIUS] = radius
        }
    }

    suspend fun getRadius(): Float {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            preferences[RADIUS] ?: 500f
        }.first()
    }

    suspend fun getTimePolicy(): Pair<Int, Int> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
            else throw exception
        }.map { preferences ->
            val timePolicy = preferences[TIME_POLICY] ?: 1
            val hours = preferences[HOURS] ?: 24
            Pair(timePolicy, hours)
        }.first()
    }

    suspend fun setTime(time: Pair<Int, Int>) {
        context.dataStore.edit { preferences ->
            preferences[TIME_POLICY] = time.first
            preferences[HOURS] = time.second
        }
    }
}
