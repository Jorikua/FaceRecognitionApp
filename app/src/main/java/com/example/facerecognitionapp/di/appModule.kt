package com.example.facerecognitionapp.di

import com.example.base.coroutines.BackgroundDispatcher.Background
import com.example.base.coroutines.CoroutinesDispatcherProvider
import com.example.base.network.ErrorHandler
import com.example.base.resource.ResourceManager
import com.example.base.resource.ResourceManagerImpl
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<ResourceManager> { ResourceManagerImpl(androidContext()) }
    single {
        CoroutinesDispatcherProvider(
            main = Dispatchers.Main,
            computation = Dispatchers.Background,
            io = Dispatchers.Background
        )
    }
    single { ErrorHandler(get()) }
    single { val options = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .build()

        FaceDetection.getClient(options)
    }
}