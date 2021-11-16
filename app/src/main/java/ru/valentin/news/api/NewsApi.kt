package ru.valentin.news.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.valentin.news.utils.Constants.Companion.API_KEY
import ru.valentin.news.models.NewsResponse

interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("apiKey")
        apiKey: String = API_KEY,
        @Query("country")
        country: String = "ru"
    ) : Response<NewsResponse>

    @GET("/v2/everything")
    suspend fun searchNews(
        @Query("q")
        query: String,
        @Query("apiKey")
        apiKey: String = API_KEY,
    ) : Response<NewsResponse>
}