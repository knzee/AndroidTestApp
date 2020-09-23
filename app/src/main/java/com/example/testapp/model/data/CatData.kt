package com.example.testapp.model.data

import java.util.*

data class CatImage(
    val file: String
)

data class CatFact(
    val text: String,
    val createdAt: Date
)