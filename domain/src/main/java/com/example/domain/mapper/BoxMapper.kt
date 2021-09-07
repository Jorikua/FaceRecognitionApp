package com.example.domain.mapper

import com.example.data.model.FaceRectResponse
import com.example.domain.model.FaceRect

fun FaceRectResponse.toFaceRect(): FaceRect {
    return FaceRect(
        left = left,
        right = right,
        top = top,
        bottom = bottom
    )
}