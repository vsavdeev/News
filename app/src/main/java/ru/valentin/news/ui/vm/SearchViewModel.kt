package ru.valentin.news.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.valentin.news.models.ArticlesItem
import ru.valentin.news.repositories.ItemRepository
import ru.valentin.news.repositories.ItemRepositoryImpl

class SearchViewModel: ViewModel(), KoinComponent {

    private val repo by inject<ItemRepository>()

    val newsList = MutableLiveData<List<ArticlesItem?>?>()
    val searchList = MutableLiveData<List<ArticlesItem?>?>()

    init {
        getNews()
    }

    fun getNews() = viewModelScope.launch{
        val response = repo.getNews()
        if (response.isSuccessful) {
            response.body().let {
                newsList.postValue(it?.articles)
            }
        }
    }

    fun searchNews(query: String) = viewModelScope.launch{
        val response = repo.searchNews(query)
        if (response.isSuccessful) {
            response.body().let {
                searchList.postValue(it?.articles)
            }
        }
    }
}