package com.arsenal.sync.features.auth.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arsenal.sync.animation.AnimateScreen
import com.arsenal.sync.features.auth.presentation.navigation.AuthRoute.SignInScreen
import com.arsenal.sync.features.auth.presentation.navigation.AuthRoute.SignUpScreen
import com.arsenal.sync.features.auth.presentation.screen.SignInScreen
import com.arsenal.sync.features.auth.presentation.screen.SignUpScreen
import com.arsenal.sync.features.auth.presentation.viewmodel.AuthViewModel

@Composable
fun AuthNavGraph(
    onOnboardingSuccess: () -> Unit
) {
    val authNavController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = authNavController,
        startDestination = SignInScreen
    ) {

        composable<SignInScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            SignInScreen(
                onSignInSuccess = onOnboardingSuccess,
                moveToSignUp = {
                    authNavController.navigate(SignUpScreen)
                },
                authViewModel = authViewModel
            )
        }

        composable<SignUpScreen>(
            popEnterTransition = AnimateScreen.rightPopEnterTransition(),
            enterTransition = AnimateScreen.leftEnterTransition(),
            popExitTransition = AnimateScreen.rightPopExitTransition(),
            exitTransition = AnimateScreen.leftExitTransition()
        ) {
            SignUpScreen(
                moveToSignIn = { authNavController.navigateUp() },
                onSignUpSuccess = onOnboardingSuccess,
                authViewModel = authViewModel
            )
        }
    }
}