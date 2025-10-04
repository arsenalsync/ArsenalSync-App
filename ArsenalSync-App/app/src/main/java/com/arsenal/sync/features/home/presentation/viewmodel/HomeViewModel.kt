package com.arsenal.sync.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsenal.sync.core.domain.model.User
import com.arsenal.sync.features.auth.domain.repository.AuthRepository
import com.arsenal.sync.features.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> =
        _userState
            .onStart { getUser() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, User())

    private fun getUser() {
        viewModelScope.launch {
            launch {
                homeRepository.getUser().collect { result ->
                    result.fold(
                        onFailure = { },
                        onSuccess = { _userState.value = it }
                    )
                }
            }
            launch {
                authRepository.getUserData()
            }
        }
    }
}