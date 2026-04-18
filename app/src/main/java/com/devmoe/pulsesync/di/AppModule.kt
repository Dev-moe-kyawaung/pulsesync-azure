package com.devmoe.pulsesync.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.devmoe.core.domain.repository.PulseRepository
import com.devmoe.core.data.repository.PulseRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePulseRepository(impl: PulseRepositoryImpl): PulseRepository = impl
    // Demonstrates SOLID - Dependency Inversion for senior-level maintainability
}

