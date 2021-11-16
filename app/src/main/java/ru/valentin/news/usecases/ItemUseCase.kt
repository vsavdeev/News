package ru.valentin.news.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Response
import ru.valentin.news.api.NewsApi
import ru.valentin.news.db.ArticleDAO
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.models.NewsResponse
import ru.valentin.news.repositories.ItemRepository
import kotlin.coroutines.CoroutineContext

class ItemUseCase : KoinComponent, CoroutineScope {

    private val repo by inject<ItemRepository>()

    var item = MutableLiveData<ArticlesItem>()

    fun getAllArticles(): LiveData<List<ArticlesItem>> {
        return repo.getAllArticles()
    }

    fun addArticle(articlesItem: ArticlesItem?) {
        repo.addArticle(articlesItem)
    }

    fun deleteArticle(articlesItem: ArticlesItem?) {
        launch { repo.deleteArticle(articlesItem) }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default
}