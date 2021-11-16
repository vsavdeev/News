package ru.valentin.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.valentin.news.models.ArticlesItem

@Database(entities = arrayOf(ArticlesItem::class), version = 1, exportSchema = false)
@TypeConverters(SourceTypeConverter::class)
abstract class ArticleDB : RoomDatabase() {
    abstract val articleDAO : ArticleDAO
}