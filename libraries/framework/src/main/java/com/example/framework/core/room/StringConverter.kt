package com.example.framework.core.room

import androidx.room.TypeConverter
import com.example.framework.extensions.fromJson
import com.example.framework.extensions.toJson

class StringConverter {
    @TypeConverter
    fun toListOfStrings(stringValue: String): List<String>? {
        return stringValue.fromJson()
    }

    @TypeConverter
    fun fromListOfStrings(listOfString: List<String>?): String {
        return listOfString.toJson()
    }
}
