package com.arsenal.sync.features.home.domain.repository

import com.arsenal.sync.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getUser(): Flow<Result<User>>
}