package com.example.facerecognitionapp

import android.app.Application
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyController
import com.example.facerecognitionapp.di.*
import com.example.facerecognitionapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initEpoxy()

    }

    private fun initKoin() {
        startKoin {
            androidContext(this@MyApp)
            loadKoinModules(
                listOf(
                    appModule,
                    controllerModule,
                    networkModule,
                    repoModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    private fun initEpoxy() {
        with(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {
            EpoxyController.defaultDiffingHandler = this
            EpoxyController.defaultModelBuildingHandler = this
        }
        EpoxyController.setGlobalDuplicateFilteringDefault(true)
    }
}