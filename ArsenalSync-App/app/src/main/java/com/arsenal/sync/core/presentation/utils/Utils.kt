package com.arsenal.sync.core.presentation.utils

import com.arsenal.sync.core.presentation.navigation.RootRoute
import com.arsenal.sync.core.presentation.navigation.RootRoute.AuthNavGraph
import com.arsenal.sync.core.presentation.navigation.RootRoute.HomeNavGraph

object Utils {
    fun getStartDestination(isReady: Boolean): RootRoute {
        return if (isReady) HomeNavGraph
        else AuthNavGraph
    }
}