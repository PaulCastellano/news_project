package com.example.repository.news

import com.example.local.dao.ReadLaterDao
import com.example.model.local.ReadLaterEntity
import com.example.remote.service.ContentService
import javax.inject.Inject


class ContentRepository @Inject constructor(
    private val service: ContentService,
    private val dao: ReadLaterDao
) {
    suspend fun getContentList(
        page: Int,
        options: Map<String, String>
    ) = service.getNewsContentList(page, options)

    suspend fun getContent(
        id: String
    ) = service.getNewContent(id)

    suspend fun getReadLaterList() = dao.getReadLaterList()

    suspend fun getReadLater(id: String) = dao.getReadLater(id)

    suspend fun deleteReadLaterById(id: String) = dao.deleteReadLaterById(id)

    suspend fun saveReadLater(entity: ReadLaterEntity) = dao.insert(entity)

    suspend fun saveReadLaterList(entityList: List<ReadLaterEntity>) = dao.insert(entityList)
}