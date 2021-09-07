package com.example.facerecognitionapp.ui.home

import com.example.domain.model.ProcessedPerson
import com.example.facerecognitionapp.base.BaseState

data class HomeState(val persons: List<ProcessedPerson> = listOf()) : BaseState