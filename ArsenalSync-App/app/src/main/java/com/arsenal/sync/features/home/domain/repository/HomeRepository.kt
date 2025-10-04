package com.arsenal.sync.features.home.domain.repository

import com.arsenal.sync.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getUser(): Flow<User>
    suspend fun getIsLocked(): Flow<Boolean>
    suspend fun setIsLocked(locked: Boolean)
    suspend fun getRadius(): Float
    suspend fun setRadius(radius: Float)
    suspend fun getTimePolicy(): Pair<Int, Int>
    suspend fun setTime(time: Pair<Int, Int>)
}