package com.vagner.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vagner.news.databinding.ItemMainBinding
import com.vagner.news.model.Article

class MainAdapter(private val onClickItem: (Article) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var article = emptyList<Article>()
        set(value) {
            field = value
           notifyDataSetChanged()
        }

    override fun getItemCount(): Int = article.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(article[position], onClickItem)

    }


    inner class ViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article, onClickItem: (Article) -> Unit) {

            binding.textTitle.text = article.title
            binding.textSource.text = article.source.name

            Glide.with(binding.imageHeadline).load(article.urlToImage).centerInside()
                .into(binding.imageHeadline)

            itemView.setOnClickListener {
                onClickItem(article)
            }
        }
    }
}