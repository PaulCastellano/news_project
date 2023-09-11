package com.example.model.remote.item

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewContent(
    @Json(name = "id") val id: String,
    @Json(name = "type") val type: String,
    @Json(name = "sectionId") val sectionID: String,
    @Json(name = "sectionName") val sectionName: String,
    @Json(name = "webPublicationDate") val webPublicationDate: String,
    @Json(name = "webTitle") val webTitle: String,
    @Json(name = "webUrl") val webUrl: String,
    @Json(name = "apiUrl") val apiUrl: String,
    @Json(name = "isHosted") val isHosted: Boolean,
    @Json(name = "pillarId") val pillarId: String,
    @Json(name = "pillarName") val pillarName: String
)
