package com.vagner.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vagner.news.databinding.ActivityDetailsBinding
import com.vagner.news.model.Article


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var article: Article


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        article = intent.getSerializableExtra("data") as Article


        binding.textDetailAuthor.text = article.author
        binding.textDetailTime.text = article.publishedAt
        binding.textDetailTitle.text = article.title
        binding.textDetailDescription.text = article.description
        binding.textDetailContent.text = article.content

        Glide.with(binding.imageDetail).load(article.urlToImage).centerInside()
            .into(binding.imageDetail)


    }


}