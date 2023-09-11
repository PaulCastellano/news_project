package com.example.model.dto

import com.example.model.local.ReadLaterEntity
import com.example.model.remote.item.NewContent

fun NewContent.toNewContentDto() = NewContentDto(
    id,
    type,
    sectionID,
    sectionName,
    webPublicationDate,
    webTitle,
    webUrl,
    apiUrl,
    isHosted,
    pillarId,
    pillarName
)

fun List<NewContent>.toNewContentDtoList() = map { it.toNewContentDto() }

fun ReadLaterEntity.toNewContentDto() = NewContentDto(
    id,
    type,
    sectionID,
    sectionName,
    webPublicationDate,
    webTitle,
    webUrl,
    apiUrl,
    isHosted,
    pillarId,
    pillarName
)

fun List<ReadLaterEntity>.toReadLaterDtoList() = map { it.toNewContentDto() }

fun NewContentDto.toReadLaterEntity() = ReadLaterEntity(
    id,
    type,
    sectionID,
    sectionName,
    webPublicationDate,
    webTitle,
    webUrl,
    apiUrl,
    isHosted,
    pillarId,
    pillarName
)