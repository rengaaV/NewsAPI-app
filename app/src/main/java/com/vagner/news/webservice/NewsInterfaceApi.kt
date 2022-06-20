package com.vagner.news.webservice


import com.vagner.news.model.NewsApiResponse
import com.vagner.news.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterfaceApi {

    @GET("top-headlines")
    fun getNewsApi(
        @Query("country") country: String = Constants.COUNTRY,
        @Query("apiKey") apikey: String = Constants.APIKEY,
        @Query("q") q: String,
        @Query("category") category: String

    ): Call<NewsApiResponse>

}