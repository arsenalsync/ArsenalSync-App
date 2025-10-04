package com.arsenal.sync.features.home.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arsenal.sync.animation.AnimateScreen
import com.arsenal.sync.features.home.presentation.navigation.HomeRoute.HomeScreen
import com.arsenal.sync.features.home.presentation.screens.HomeScreen

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
                onSettingClick = navigateToSettings
            )
        }
    }
}