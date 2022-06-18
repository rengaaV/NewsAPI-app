package com.vagner.news.webservice


import com.vagner.news.model.NewsApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterfaceApi {

    @GET("top-headlines")
    fun getNewsCountry(
        @Query("country") country: String,
        @Query("apiKey") apikey: String
    ): Call<NewsApiResponse>

    fun getNewsCategory(
        @Query("category") category: String,
        @Query("apiKey") apikey: String
    ): Call<NewsApiResponse>

    fun getNewsSearch(
        @Query("q") q: String,
        @Query("apiKey") apikey: String
    )  : Call<NewsApiResponse>

}