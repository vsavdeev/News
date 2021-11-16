package ru.valentin.news.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.valentin.news.db.ArticleDAO
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.usecases.ItemUseCase
import kotlin.coroutines.coroutineContext

class ItemViewModel: ViewModel(), KoinComponent {

    private val useCase by inject<ItemUseCase>()

    private val item = useCase.item.map { it }

    val title = item.map { mapArticleItem(it) }
    val description = item.map { it.description }
    val publishedAt = item.map { it.publishedAt }
    val imageUrl = item.map { it.urlToImage }

    private fun mapArticleItem(item : ArticlesItem) : String {
        return item.title.toString()
    }

    fun addArticle() {
        viewModelScope.launch(Dispatchers.IO) { useCase.addArticle(item.value) }
    }
}