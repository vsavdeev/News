package ru.valentin.news.repositories

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.Response
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.models.NewsResponse

interface ItemRepository {

    fun getAllArticles() : LiveData<List<ArticlesItem>>

    fun addArticle(articlesItem: ArticlesItem?)

    fun deleteArticle(articlesItem: ArticlesItem?)

    suspend fun getNews(): Response<NewsResponse>

    suspend fun searchNews(query: String): Response<NewsResponse>
}