package com.example.model.remote.base

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleItemResponse<V>(
    @Json(name="response") val itemInfo: SingleItemInfo<V>
)