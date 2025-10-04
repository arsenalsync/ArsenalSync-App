package com.arsenal.sync.features.auth.presentation.viewmodel

import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arsenal.sync.R
import com.arsenal.sync.common.domain.state.AppState
import com.arsenal.sync.di.ResourceManager
import com.arsenal.sync.features.auth.domain.model.SignUpDetail
import com.arsenal.sync.features.auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _authState = MutableStateFlow<AppState<String, String>>(AppState.Idle)
    val authState: StateFlow<AppState<String, String>> = _authState

    val signInForm = MutableStateFlow(List(2) { false })
    val signUpForm = MutableStateFlow(List(3) { false })

    var signInEmailText = mutableStateOf("")
    var signInPasswordText = mutableStateOf("")
    var signInIsRemember = mutableStateOf(true)
    var signUpEmailText = mutableStateOf("")
    var signUpPasswordText = mutableStateOf("")
    var signUpFirstNameText = mutableStateOf("")
    var signUpIsRemember = mutableStateOf(true)


    fun signUp(email: String, password: String, firstName: String, isRemember: Boolean) {
        viewModelScope.launch {
            _authState.value = AppState.Loading
            try {
                val resultList = MutableList(3) { false }

                resultList[0] = firstName.length < 3
                resultList[1] = (!Patterns.EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
                        )

                resultList[2] = (password.length < 6)

                if (resultList.all { !it }) {
                    signUpForm.value = resultList
                    val signUpDetail = SignUpDetail(
                        firstName = firstName,
                        email = email,
                        password = password,
                        isRemember = isRemember
                    )
                    val result = authRepository.signUp(signUpDetail)
                    _authState.value = result.fold(
                        onSuccess = { AppState.Success(it) },
                        onFailure = {
                            AppState.Error(
                                it.message ?: resourceManager.getString(R.string.sign_up_failed)
                            )
                        }
                    )
                } else {
                    _authState.value = AppState.Idle
                    signUpForm.value = resultList
                }
            } catch (e: Exception) {
                _authState.value = AppState.Error(
                    e.message ?: resourceManager.getString(R.string.unknown_error_occurred)
                )
            }
        }
    }

    fun signIn(email: String, password: String, isRemember: Boolean) {
        viewModelScope.launch {
            _authState.value = AppState.Loading
            try {
                val resultList = MutableList(2) { false }

                resultList[0] = (!Patterns.EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
                        )

                resultList[1] = (password.length < 6)

                if (resultList.all { !it }) {
                    signInForm.value = resultList
                    val signUpDetail =
                        SignUpDetail(email = email, password = password, isRemember = isRemember)
                    val result = authRepository.signIn(signUpDetail)
                    _authState.value = result.fold(
                        onSuccess = { AppState.Success(it) },
                        onFailure = {
                            AppState.Error(
                                it.message ?: resourceManager.getString(R.string.sign_in_failed)
                            )
                        }
                    )
                } else {
                    _authState.value = AppState.Idle
                    signInForm.value = resultList
                }
            } catch (e: Exception) {
                _authState.value = AppState.Error(
                    e.message ?: resourceManager.getString(R.string.unknown_error_occurred)
                )
            }
        }
    }

    init {
        getAuthCredentials()
    }

    private fun getAuthCredentials() {
        viewModelScope.launch {
            try {
                val result = authRepository.getAuthCredentials()

                signInEmailText.value = result.first
                signInPasswordText.value = result.second
            } catch (_: Exception) {
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _authState.value = AppState.Loading
            try {
                val result = authRepository.signOut()
                _authState.value = result.fold(
                    onSuccess = { AppState.Success(it) },
                    onFailure = {
                        AppState.Error(
                            it.message ?: resourceManager.getString(R.string.sign_out_failed)
                        )
                    }
                )
            } catch (e: Exception) {
                _authState.value = AppState.Error(
                    e.message ?: resourceManager.getString(R.string.unknown_error_occurred)
                )
            }
        }
    }

    fun resetState() {
        _authState.value = AppState.Idle
    }
}