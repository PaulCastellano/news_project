package com.example.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.framework.core.room.StringConverter
import com.example.local.dao.ReadLaterDao
import com.example.model.local.ReadLaterEntity

@Database(
    entities = [
        ReadLaterEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(StringConverter::class)
abstract class NewDatabase: RoomDatabase() {
    abstract fun readLaterDao(): ReadLaterDao
}