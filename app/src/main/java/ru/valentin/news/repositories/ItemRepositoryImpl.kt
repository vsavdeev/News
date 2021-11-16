package ru.valentin.news.repositories

import androidx.lifecycle.LiveData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import ru.valentin.news.api.NewsApi
import ru.valentin.news.db.ArticleDAO
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.models.NewsResponse

class ItemRepositoryImpl: KoinComponent, ItemRepository {

    private val api by inject<NewsApi>()
    private val dao by inject<ArticleDAO>()

    override fun getAllArticles(): LiveData<List<ArticlesItem>> {
        return dao.getAllArticles()
    }

    override fun addArticle(articlesItem: ArticlesItem?) {
        dao.addArticle(articlesItem)
    }

    override fun deleteArticle(articlesItem: ArticlesItem?) {
        dao.deleteArticle(articlesItem)
    }

    override suspend fun getNews(): Response<NewsResponse> {
        return api.getNews()
    }

    override suspend fun searchNews(query: String): Response<NewsResponse> {
        return api.getNews(query)
    }
}