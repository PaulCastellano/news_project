package com.example.model.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleItemInfo<V>(
    @Json(name = "status") val status: String,
    @Json(name = "userTier") val userTier: String,
    @Json(name = "total") val total: Int,
    @Json(name = "content") val content: V,
)