package ru.valentin.news.db

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.valentin.news.models.ArticlesItem

@Dao
interface ArticleDAO {

    @Query("select * from articlesitem")
    fun getAllArticles() : LiveData<List<ArticlesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArticle(articlesItem: ArticlesItem?)

    @Delete
    fun deleteArticle(articlesItem: ArticlesItem?)
}