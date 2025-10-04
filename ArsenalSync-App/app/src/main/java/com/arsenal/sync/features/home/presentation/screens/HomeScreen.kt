package com.arsenal.sync.features.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arsenal.sync.features.home.presentation.components.GunVerificationCard
import com.arsenal.sync.features.home.presentation.components.HomeHeader
import com.arsenal.sync.features.home.presentation.components.VerifiedDashboard
import com.arsenal.sync.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    onGeofencingClick: () -> Unit,
    onTimePolicyClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userState by homeViewModel.userState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        HomeHeader(userName = userState.firstName, onSettingClick = onSettingClick)

        if (!userState.isVerified) {
            GunVerificationCard()
        } else {
            VerifiedDashboard(
                onGeofencingClick = onGeofencingClick,
                onTimePolicyClick = onTimePolicyClick
            )
        }
    }
}