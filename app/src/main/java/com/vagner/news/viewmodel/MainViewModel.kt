package com.vagner.news.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vagner.news.model.Article
import com.vagner.news.model.NewsApiResponse
import com.vagner.news.webservice.StartRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val repository by lazy { StartRetrofit.init() }
    var liveList = MutableLiveData<List<Article>>()
    var liveError = MutableLiveData<String>()


    fun getNewsCountry(category: String , q : String ) {
        repository.getNewsApi(category = category, q = q).enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        liveList.postValue(it.articles)
                    }
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                liveError.postValue(t.message)
            }
        })
    }

}