package com.example.domain.usecase

import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.domain.UseCase
import com.example.base.network.ErrorHandler
import com.example.data.Repository
import com.example.domain.mapper.toFaceRect
import com.example.domain.model.FaceRect
import kotlinx.coroutines.withContext

class CalculateFacesUseCase(
    private val repository: Repository,
    private val dispatcherProvider: CoroutinesDispatcherProvider,
    errorHandler: ErrorHandler
) : UseCase<CalculateFacesUseCase.Params, List<FaceRect>>(errorHandler) {

    class Params(val width: Int, val height: Int, val imageRes: Int)

    override suspend fun run(params: Params): List<FaceRect> {
        return withContext(dispatcherProvider.io) {
            repository.calculateFaces(params.width, params.height, params.imageRes)
                .map { it.toFaceRect() }
        }
    }
}