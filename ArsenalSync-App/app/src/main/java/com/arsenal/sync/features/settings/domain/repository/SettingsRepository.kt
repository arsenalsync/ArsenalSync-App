package com.arsenal.sync.features.settings.domain.repository

import com.arsenal.sync.core.domain.utils.ThemeOption
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    suspend fun setTheme(themeOption: ThemeOption)
    suspend fun getTheme(): Flow<ThemeOption>
}