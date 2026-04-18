package com.devmoe.core.domain.usecase

import com.devmoe.core.domain.model.PulseData
import com.devmoe.core.domain.repository.PulseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPulsesUseCase @Inject constructor(
    private val repository: PulseRepository
) {
    operator fun invoke(): Flow<List<PulseData>> = repository.getPulses()
    // Clean Architecture - UseCase layer keeps business logic testable and independent
}

