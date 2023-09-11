package com.example.model.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PaginationInfo<V>(
    @Json(name = "status") val status: String?,
    @Json(name = "userTier") val userTier: String?,
    @Json(name = "total") val total: Int?,
    @Json(name = "startIndex") val startIndex: Int?,
    @Json(name = "pageSize") val pageSize: Int?,
    @Json(name = "currentPage") val currentPage: Int?,
    @Json(name = "pages") val pages: Int?,
    @Json(name = "results") val results: List<V>?,
)
