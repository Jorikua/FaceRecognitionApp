package com.example.domain.usecase

import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.domain.UseCase
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.domain.mapper.toProcessedPerson
import com.example.domain.model.ProcessedPerson
import kotlinx.coroutines.withContext

class GetPersonsUseCase(
    private val repository: Repository,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    errorHandler: ErrorHandler
) : UseCase<Unit, List<ProcessedPerson>>(errorHandler) {
    override suspend fun run(params: Unit): List<ProcessedPerson> {
        return withContext(dispatcherProvider.io) {
            val (withFaces, withoutFaces) = repository.getPersons().partition { it.width != null }

            val sortedFaces = withFaces.sortedByDescending {
                val width = it.width!!
                width * width
            }.map { it.toProcessedPerson() }

            val sortedWithoutFaces =
                withoutFaces.sortedBy { it.name }.map { it.toProcessedPerson() }

            sortedFaces + sortedWithoutFaces
        }
    }
}