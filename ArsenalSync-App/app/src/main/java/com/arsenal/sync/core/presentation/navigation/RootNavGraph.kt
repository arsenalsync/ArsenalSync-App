package com.arsenal.sync.core.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arsenal.sync.animation.AnimateScreen
import com.arsenal.sync.core.presentation.navigation.RootRoute.AuthNavGraph
import com.arsenal.sync.core.presentation.navigation.RootRoute.HomeNavGraph
import com.arsenal.sync.core.presentation.navigation.RootRoute.SettingNavGraph
import com.arsenal.sync.features.auth.presentation.navigation.AuthNavGraph
import com.arsenal.sync.features.home.presentation.navigation.HomeNavGraph
import com.arsenal.sync.features.settings.presentation.navigation.SettingsNavGraph

@Composable
fun RootNavGraph(
    rootNavController: NavHostController,
    startDestination: RootRoute,
    onBackOrFinish: () -> Unit,
) {
    Scaffold { paddingValues ->
        NavHost(
            navController = rootNavController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable<AuthNavGraph>(
                popEnterTransition = AnimateScreen.rightPopEnterTransition(),
                enterTransition = AnimateScreen.leftEnterTransition(),
                popExitTransition = AnimateScreen.rightPopExitTransition(),
                exitTransition = AnimateScreen.leftExitTransition()
            ) {
                AuthNavGraph(
                    onBackOrFinish = onBackOrFinish,
                    onOnboardingSuccess = {
                        rootNavController.navigate(HomeNavGraph) {
                            popUpTo(AuthNavGraph) {
                                inclusive = true
                            }
                        }
                    }
                )
            }

            composable<HomeNavGraph>(
                popEnterTransition = AnimateScreen.rightPopEnterTransition(),
                enterTransition = AnimateScreen.leftEnterTransition(),
                popExitTransition = AnimateScreen.rightPopExitTransition(),
                exitTransition = AnimateScreen.leftExitTransition()
            ) {
                HomeNavGraph(
                    navigateToSettings = {
                        rootNavController.navigate(SettingNavGraph)
                    }
                )
            }

            composable<SettingNavGraph>(
                popEnterTransition = AnimateScreen.rightPopEnterTransition(),
                enterTransition = AnimateScreen.leftEnterTransition(),
                popExitTransition = AnimateScreen.rightPopExitTransition(),
                exitTransition = AnimateScreen.leftExitTransition()
            ) {
                SettingsNavGraph(
                    onSignOut = {
                        rootNavController.navigate(AuthNavGraph) {
                            popUpTo(HomeNavGraph) {
                                inclusive = true
                            }
                        }
                    },
                    onBackOrFinish = onBackOrFinish
                )
            }
        }
    }
}