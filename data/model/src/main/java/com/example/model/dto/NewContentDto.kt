package com.example.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewContentDto(
    val id: String,
    val type: String,
    val sectionID: String,
    val sectionName: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String,
    val apiUrl: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    var isReadLaterElement: Boolean = false
) : Parcelable