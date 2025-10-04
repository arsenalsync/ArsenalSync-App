package com.arsenal.sync.features.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arsenal.sync.animation.AnimateScreen
import com.arsenal.sync.features.geofencing.presentation.screen.GeofencingScreen
import com.arsenal.sync.features.home.presentation.navigation.HomeRoute.HomeScreen
import com.arsenal.sync.features.home.presentation.screens.HomeScreen
import com.arsenal.sync.features.time_policy.presentation.screen.TimePolicyScreen

@Composable
fun HomeNavGraph(
    navigateToSettings: () -> Unit
) {
    val homeNavController = rememberNavController()

    NavHost(
        navController = homeNavController,
        startDestination = HomeScreen
    ) {

        composable<HomeScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            HomeScreen(
                onSettingClick = navigateToSettings,
                onGeofencingClick = { homeNavController.navigate(route = HomeRoute.GeoFencingScreen) },
                onTimePolicyClick = { homeNavController.navigate(route = HomeRoute.TimePolicyScreen) }
            )
        }

        composable<HomeRoute.GeoFencingScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            GeofencingScreen(onBackClick = { homeNavController.navigateUp() })
        }

        composable<HomeRoute.TimePolicyScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            TimePolicyScreen(onBackClick = { homeNavController.navigateUp() })
        }
    }
}