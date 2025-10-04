package com.arsenal.sync.features.settings.data

import com.arsenal.sync.core.data.Preferences
import com.arsenal.sync.core.domain.utils.ThemeOption
import com.arsenal.sync.features.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferences: Preferences
) : SettingsRepository {
    override suspend fun setTheme(themeOption: ThemeOption) {
        return try {
            withContext(Dispatchers.IO) {
                preferences.setTheme(themeOption)
            }
        } catch (_: Exception) {
        }
    }

    override suspend fun getTheme(): Flow<ThemeOption> {
        return withContext(Dispatchers.IO) {
            preferences.getTheme()
        }
    }
}