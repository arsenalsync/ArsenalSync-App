package com.arsenal.sync.features.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arsenal.sync.features.home.presentation.components.HomeHeader
import com.arsenal.sync.features.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    onSettingClick: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val userState by homeViewModel.userState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp)
    ) {
        HomeHeader(userName = userState.firstName, onSettingClick = onSettingClick)

        Text(text = "Is use verified - " + userState.isVerified.toString())
    }
}