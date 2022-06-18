package com.vagner.news.webservice


import com.vagner.news.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object StartRetrofit {

    fun init(): NewsInterfaceApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsInterfaceApi::class.java)
    }
}