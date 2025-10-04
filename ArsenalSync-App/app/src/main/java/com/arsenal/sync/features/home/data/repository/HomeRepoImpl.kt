package com.arsenal.sync.features.home.data.repository

import com.arsenal.sync.core.data.Preferences
import com.arsenal.sync.core.domain.model.User
import com.arsenal.sync.features.home.domain.repository.HomeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class HomeRepoImpl @Inject constructor(
    private val preferences: Preferences
) : HomeRepository {
    override suspend fun getUser(): Flow<Result<User>> {
        return preferences.getUser()
    }
}