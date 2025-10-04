package com.arsenal.sync.features.settings.presentation.navigation

import kotlinx.serialization.Serializable

sealed class SettingsRoute {

    // Settings related screens

    @Serializable
    data object SettingsScreen : SettingsRoute()
}