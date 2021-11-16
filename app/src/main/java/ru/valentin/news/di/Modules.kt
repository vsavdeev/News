package ru.valentin.news.di

import android.app.Application
import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.valentin.news.api.NewsApi
import ru.valentin.news.db.ArticleDAO
import ru.valentin.news.db.ArticleDB
import ru.valentin.news.repositories.ItemRepository
import ru.valentin.news.repositories.ItemRepositoryImpl
import ru.valentin.news.ui.vm.ItemViewModel
import ru.valentin.news.ui.vm.SaveViewModel
import ru.valentin.news.ui.vm.SearchViewModel
import ru.valentin.news.usecases.ItemUseCase
import ru.valentin.news.utils.Constants

val appModule = module {

    // single instance of NewsRepository
    single<ItemRepository> { ItemRepositoryImpl() }

    factory { provideRetrofit(get()) }
    factory { provideClient(get()) }
    factory { provideInterceptor() }
    single { provideNewsApi(get()) }
    single { ItemUseCase() }
    single { provideArticleDB(androidApplication()) }
    single { provideArticleDAO(get()) }

    // MyViewModel ViewModel
    viewModel { SearchViewModel() }
    viewModel { ItemViewModel() }
    viewModel { SaveViewModel() }
}

fun provideRetrofit(client: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideClient(logging: HttpLoggingInterceptor) : OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
}

fun provideInterceptor() : HttpLoggingInterceptor{
    return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideNewsApi(retrofit: Retrofit) : NewsApi{
    return retrofit.create(NewsApi::class.java)
}

fun provideArticleDB(application: Application) : ArticleDB {
    return Room.databaseBuilder(application, ArticleDB::class.java, "ARTICLEDB")
        .fallbackToDestructiveMigration()
        .build()
}

fun provideArticleDAO(articleDB: ArticleDB) : ArticleDAO {
    return articleDB.articleDAO
}