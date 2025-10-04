package com.arsenal.sync.features.home.data.repository

import com.arsenal.sync.core.data.Preferences
import com.arsenal.sync.core.domain.model.User
import com.arsenal.sync.features.home.domain.repository.HomeRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class HomeRepoImpl @Inject constructor(
    private val preferences: Preferences
) : HomeRepository {
    override suspend fun getUser(): Flow<User> {
        return preferences.getUser()
    }

    override suspend fun getIsLocked(): Flow<Boolean> {
        return preferences.getIsLocked()
    }

    override suspend fun setIsLocked(locked: Boolean) {
        preferences.setIsLocked(locked)
    }

    override suspend fun getRadius(): Float {
        return preferences.getRadius()
    }

    override suspend fun setRadius(radius: Float) {
        preferences.setRadius(radius)
    }

    override suspend fun getTimePolicy(): Pair<Int, Int> {
        return preferences.getTimePolicy()
    }

    override suspend fun setTime(time: Pair<Int, Int>) {
        preferences.setTime(time)
    }
}