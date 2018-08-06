package com.example.marcosalinas.beetrack_test.adapters

import android.arch.lifecycle.LiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener
import com.example.marcosalinas.beetrack_test.viewholders.FavoritesViewHolder

class FavoritesAdapter(checkboxListener: CheckboxListener) : RecyclerView.Adapter<FavoritesViewHolder>() {

  private var articles : List<Article> = ArrayList()
  private val checkboxListener = checkboxListener

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
    return FavoritesViewHolder(view, checkboxListener)
  }

  override fun getItemCount(): Int {
    return articles.size
  }

  override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
    holder.updateUI(articles[position])
  }

  fun setArticles(favArticles: List<Article>){
    articles = favArticles
    notifyDataSetChanged()
  }
}