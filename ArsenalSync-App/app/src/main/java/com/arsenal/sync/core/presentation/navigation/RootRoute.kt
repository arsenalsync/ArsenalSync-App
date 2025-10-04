package com.arsenal.sync.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed class RootRoute {

    // screens that has separate module

    @Serializable
    data object AuthNavGraph : RootRoute()

    @Serializable
    data object HomeNavGraph : RootRoute()

    @Serializable
    data object SettingNavGraph : RootRoute()
}