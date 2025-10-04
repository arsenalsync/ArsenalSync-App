package com.arsenal.sync.di

import com.arsenal.sync.core.data.Preferences
import com.arsenal.sync.features.auth.data.AuthRepositoryImpl
import com.arsenal.sync.features.auth.domain.repository.AuthRepository
import com.arsenal.sync.features.home.data.repository.HomeRepoImpl
import com.arsenal.sync.features.home.domain.repository.HomeRepository
import com.arsenal.sync.features.settings.data.SettingsRepositoryImpl
import com.arsenal.sync.features.settings.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        supAuth: Auth,
        sessionManager: SessionManager,
        supData: Postgrest,
        preferences: Preferences,
        resourceManager: ResourceManager
    ): AuthRepository =
        AuthRepositoryImpl(supAuth, sessionManager, supData, preferences, resourceManager)

    @Provides
    @Singleton
    fun provideHomeRepository(
        preferences: Preferences
    ): HomeRepository = HomeRepoImpl(preferences)

    @Provides
    @Singleton
    fun provideSettingsRepository(
        preferences: Preferences
    ): SettingsRepository = SettingsRepositoryImpl(preferences)
}