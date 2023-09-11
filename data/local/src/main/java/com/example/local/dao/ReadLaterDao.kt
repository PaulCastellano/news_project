package com.example.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.framework.core.room.BaseDao
import com.example.model.local.ReadLaterEntity

@Dao
interface ReadLaterDao: BaseDao<ReadLaterEntity> {
    @Query("SELECT * FROM ${ReadLaterEntity.TABLE_NAME}")
    suspend  fun getReadLaterList(): List<ReadLaterEntity>

    @Query("SELECT * FROM ${ReadLaterEntity.TABLE_NAME} WHERE id = :id")
    suspend  fun getReadLater(id: String): ReadLaterEntity?

    @Query("DELETE FROM ${ReadLaterEntity.TABLE_NAME}")
    suspend  fun deleteReadLater()

    @Query("DELETE FROM ${ReadLaterEntity.TABLE_NAME} WHERE id = :id")
    suspend  fun deleteReadLaterById(id: String)
}