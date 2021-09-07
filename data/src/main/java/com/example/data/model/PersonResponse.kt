package com.example.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "img")
    val img: String
)
