package com.example.facerecognitionapp.di

import com.example.data.Repository
import com.example.data.source.LocalDataSource
import com.example.data.source.RemoteDataSource
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get(), get(), get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get(), get()) }
}