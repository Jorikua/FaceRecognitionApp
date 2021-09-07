package com.example.facerecognitionapp.di

import com.example.facerecognitionapp.ui.details.DetailsViewModel
import com.example.facerecognitionapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}