package com.arsenal.sync.features.auth.domain.repository

import com.arsenal.sync.features.auth.domain.model.SignUpDetail

interface AuthRepository {
    suspend fun signIn(signUpDetail: SignUpDetail): Result<String>
    suspend fun signUp(signUpDetail: SignUpDetail): Result<String>
    suspend fun signOut(): Result<String>
    suspend fun getAuthCredentials(): Result<Pair<String, String>>
    suspend fun getUserData()
}