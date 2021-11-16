package ru.valentin.news.models

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NewsResponse(
	val totalResults: Int? = null,
	val articles: List<ArticlesItem?>? = null,
	val status: String? = null
)

@Entity
data class ArticlesItem(
	@PrimaryKey
	val publishedAt: String,
	val urlToImage: String? = null,
	val description: String? = null,
	val source: Source? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null
)

data class Source(
	val name: String? = null,
	val id: Any? = null
)

