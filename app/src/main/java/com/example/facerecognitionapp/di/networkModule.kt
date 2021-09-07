package com.example.facerecognitionapp.di

import com.squareup.moshi.Moshi
import org.koin.dsl.module

val networkModule = module {
    single { Moshi.Builder().build() }
}