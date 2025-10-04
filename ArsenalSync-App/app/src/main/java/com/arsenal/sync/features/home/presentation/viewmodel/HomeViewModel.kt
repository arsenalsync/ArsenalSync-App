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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _isLocked = MutableStateFlow(false)
    val isLocked: StateFlow<Boolean> =
        _isLocked
            .onStart { getIsLocked() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val customHours = MutableStateFlow("24")
    val timePolicy = MutableStateFlow(1)

    private val _radius = MutableStateFlow(500f)
    val radius: StateFlow<Float> =
        _radius
            .onStart { getRadius() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, 500f)


    private val _userState = MutableStateFlow(User())
    val userState: StateFlow<User> =
        _userState
            .onStart { getUser() }
            .stateIn(viewModelScope, SharingStarted.Eagerly, User())

    init {
        getTimePolicy()
    }

    private fun getUser() {
        viewModelScope.launch {
            launch {
                homeRepository.getUser().collect { result ->
                    _userState.value = result
                }
            }
            launch {
                authRepository.getUserData()
            }
        }
    }

    fun updateRadius(radius: Float) {
        _radius.update { radius }
    }

    private fun getRadius() {
        viewModelScope.launch {
            _radius.update { homeRepository.getRadius() }
        }
    }

    private fun getTimePolicy() {
        viewModelScope.launch {
            val res = homeRepository.getTimePolicy()
            timePolicy.update { res.first }
            customHours.update { res.second.toString() }
        }
    }

    private fun getIsLocked() {
        viewModelScope.launch {
            homeRepository.getIsLocked().collect { res ->
                _isLocked.update { res }
            }
        }
    }

    fun onSetIsLocked(isLocked: Boolean) {
        viewModelScope.launch {
            homeRepository.setIsLocked(!isLocked)
        }
    }

    fun saveRadius(radius: Float) {
        viewModelScope.launch {
            homeRepository.setRadius(radius)
        }
    }

    fun updateCustomHours(hours: String) {
        customHours.update { hours }
    }

    fun updateTimePolicy(policyType: Int) {
        timePolicy.update { policyType }
    }

    fun saveTime() {
        viewModelScope.launch {
            homeRepository.setTime(Pair(timePolicy.value, customHours.value.toInt()))
        }
    }

    fun resetState() {
        getRadius()
        getTimePolicy()
        getIsLocked()
    }
}