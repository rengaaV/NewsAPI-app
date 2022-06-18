package com.vagner.news.model

import java.io.Serializable

data class NewsApiResponse(
    val articles: List<Article> ,
    val status: String,
    val totalResults: Int
): Serializable