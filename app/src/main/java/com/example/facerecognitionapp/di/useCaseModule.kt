package com.example.facerecognitionapp.di

import com.example.domain.usecase.CalculateFacesUseCase
import com.example.domain.usecase.GetPersonsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetPersonsUseCase(get(), get(), get()) }
    factory { CalculateFacesUseCase(get(), get(), get()) }
}