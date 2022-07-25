package com.ahmet.ahmetapi_deneme_1.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success:Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Hero> = emptyList(),
    val lastUpdated: Long? =null
)
