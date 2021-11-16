package ru.valentin.news.db

import androidx.room.TypeConverter
import org.json.JSONObject
import ru.valentin.news.models.Source

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return JSONObject().apply {
            put("name", source.name)
        }.toString()
    }
    @TypeConverter
    fun toSource(source: String): Source {
        val json = JSONObject(source)
        return Source(json.getString("name"))
    }
}