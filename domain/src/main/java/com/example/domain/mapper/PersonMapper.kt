package com.example.domain.mapper

import com.example.data.model.ProcessedPersonResponse
import com.example.domain.model.ProcessedPerson

fun ProcessedPersonResponse.toProcessedPerson(): ProcessedPerson {
    return ProcessedPerson(
        name = name,
        imageRes = imageRes,
        width = width
    )
}