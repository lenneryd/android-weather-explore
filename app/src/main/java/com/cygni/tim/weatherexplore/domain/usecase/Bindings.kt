package com.cygni.tim.weatherexplore.domain.usecase

import com.cygni.tim.weatherexplore.domain.repository.ClockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ClockUseCaseModule {
    @Provides
    fun clockUseCase(repository: ClockRepository): ClockUseCase = ClockUseCase(repository)
}

