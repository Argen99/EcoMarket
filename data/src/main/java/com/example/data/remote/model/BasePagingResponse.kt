package com.example.data.remote.model

data class BasePagingResponse<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<T>
)