package com.arsenal.sync.features.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arsenal.sync.animation.AnimateScreen
import com.arsenal.sync.features.geofencing.presentation.screen.GeofencingScreen
import com.arsenal.sync.features.home.presentation.navigation.HomeRoute.HomeScreen
import com.arsenal.sync.features.home.presentation.screens.HomeScreen
import com.arsenal.sync.features.home.presentation.viewmodel.HomeViewModel
import com.arsenal.sync.features.time_policy.presentation.screen.TimePolicyScreen

@Composable
fun HomeNavGraph(
    navigateToSettings: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
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
                onTimePolicyClick = { homeNavController.navigate(route = HomeRoute.TimePolicyScreen) },
                homeViewModel = homeViewModel
            )
        }

        composable<HomeRoute.GeoFencingScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            GeofencingScreen(
                homeViewModel = homeViewModel,
                onBackClick = { homeNavController.navigateUp() })
        }

        composable<HomeRoute.TimePolicyScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            TimePolicyScreen(
                homeViewModel = homeViewModel,
                onBackClick = { homeNavController.navigateUp() })
        }
    }
}