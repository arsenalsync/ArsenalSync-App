package com.arsenal.sync.features.home.presentation.navigation

import kotlinx.serialization.Serializable

sealed class HomeRoute {

    // Home related screens

    @Serializable
    data object HomeScreen : HomeRoute()
}