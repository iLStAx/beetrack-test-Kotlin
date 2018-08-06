package com.example.marcosalinas.beetrack_test.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.fragments.FavoritesViewModel
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener
import com.example.marcosalinas.beetrack_test.viewholders.NewsViewHolder

class NewsAdapter(checkboxListener: CheckboxListener) : RecyclerView.Adapter<NewsViewHolder>(){

  private var articles : List<Article> = ArrayList()
  private var favArticles : List<Article> = ArrayList()
  private var checkboxListener = checkboxListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
    return NewsViewHolder(view, checkboxListener)
  }

  override fun getItemCount(): Int {
    return articles.size
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    holder.updateUI(articles[position], isCheked(articles[position]))
  }

  fun setArticles(webArticles : List<Article>){
    articles = webArticles
    notifyDataSetChanged()
  }

  fun setFavArticles(fArticles : List<Article>){ favArticles = fArticles }

  fun isCheked(article : Article) : Boolean{
    for (favArticle in favArticles) {
      if(favArticle.title.equals(article.title)){
        return true
      }
    }
    return false
  }
}