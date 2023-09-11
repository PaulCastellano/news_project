package com.example.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ReadLaterEntity.TABLE_NAME)
data class ReadLaterEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID) val id: String,
    @ColumnInfo(name = COLUMN_TYPE) val type: String,
    @ColumnInfo(name = COLUMN_SECTION_ID) val sectionID: String,
    @ColumnInfo(name = COLUMN_SECTION_NAME) val sectionName: String,
    @ColumnInfo(name = COLUMN_WEV_PUBLICATION_DATE) val webPublicationDate: String,
    @ColumnInfo(name = COLUMN_WEB_TITLE) val webTitle: String,
    @ColumnInfo(name = COLUMN_WEB_URL) val webUrl: String,
    @ColumnInfo(name = COLUMN_API_URL) val apiUrl: String,
    @ColumnInfo(name = COLUMN_IS_HOSTED) val isHosted: Boolean,
    @ColumnInfo(name = COLUMN_PILLAR_ID) val pillarId: String,
    @ColumnInfo(name = COLUMN_PILLAR_NAME) val pillarName: String
) {
    companion object {
        const val TABLE_NAME = "read_later"
        const val COLUMN_ID = "id"
        const val COLUMN_TYPE = "type"
        const val COLUMN_SECTION_ID = "sectionId"
        const val COLUMN_SECTION_NAME = "sectionName"
        const val COLUMN_WEV_PUBLICATION_DATE = "webPublicationDate"
        const val COLUMN_WEB_TITLE = "webTitle"
        const val COLUMN_WEB_URL = "webUrl"
        const val COLUMN_API_URL = "apiUrl"
        const val COLUMN_IS_HOSTED = "isHosted"
        const val COLUMN_PILLAR_ID = "pillarId"
        const val COLUMN_PILLAR_NAME = "pillarName"
    }
}