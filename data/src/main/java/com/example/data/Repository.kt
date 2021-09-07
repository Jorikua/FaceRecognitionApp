package com.example.data

import android.graphics.Bitmap
import com.example.data.model.FaceRectResponse
import com.example.data.model.ProcessedPersonResponse
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetector
import kotlinx.coroutines.suspendCancellableCoroutine

class Repository(
    private val detector: FaceDetector,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getPersons(): List<ProcessedPersonResponse> {
        val persons = remoteDataSource.getPersons()
        val images = localDataSource.getImagesBitmap()

        return images.mapIndexed { index, bitmap ->
            val person = persons[index]
            val res = localDataSource.resources[index]
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            val response = processImage(person.name, res, inputImage)
            bitmap.recycle()

            response
        }
    }

    suspend fun calculateFaces(targetWidth: Int, targetHeight: Int, imageRes: Int): List<FaceRectResponse> {
        val bitmap = localDataSource.getImageBitmap(imageRes)

        val scaleFactor = Math.max(
            bitmap.width.toFloat() / targetWidth.toFloat(),
            bitmap.height.toFloat() / targetHeight.toFloat()
        )

        val resizedBitmap = Bitmap.createScaledBitmap(
            bitmap,
            (bitmap.width / scaleFactor).toInt(),
            (bitmap.height / scaleFactor).toInt(),
            true
        )

        return getFacesRects(resizedBitmap)
    }

    private suspend fun getFacesRects(bitmap: Bitmap) =
        suspendCancellableCoroutine<List<FaceRectResponse>> {
            val image = InputImage.fromBitmap(bitmap, 0)
            detector.process(image)
                .addOnSuccessListener { faces ->
                    it.resumeWith(Result.success(faces.mapNotNull {
                        val box = it.boundingBox
                        FaceRectResponse(box.left, box.top, box.right, box.bottom)
                    }))
                }.addOnFailureListener { _ ->
                    it.resumeWith(Result.success(emptyList()))
                }
        }

    private suspend fun processImage(name: String, res: Int, inputImage: InputImage) =
        suspendCancellableCoroutine<ProcessedPersonResponse> {
            detector.process(inputImage)
                .addOnSuccessListener { faces ->
                    val face = faces.maxByOrNull { it.boundingBox.width() * it.boundingBox.width() }
                    if (face == null) {
                        it.resumeWith(Result.success(ProcessedPersonResponse(name, res, null)))
                        return@addOnSuccessListener
                    }

                    it.resumeWith(
                        Result.success(
                            ProcessedPersonResponse(
                                name,
                                res,
                                face.boundingBox.width()
                            )
                        )
                    )
                }
                .addOnFailureListener { _ ->
                    it.resumeWith(Result.success(ProcessedPersonResponse(name, res, null)))
                }
        }
}