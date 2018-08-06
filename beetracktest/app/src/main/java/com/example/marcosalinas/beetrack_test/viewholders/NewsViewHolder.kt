package com.example.marcosalinas.beetrack_test.viewholders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.activities.WebViewActivity
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener
import java.text.SimpleDateFormat

class NewsViewHolder(itemView: View?, checkboxListener: CheckboxListener) : RecyclerView.ViewHolder(itemView) {

  private val imageArticle : ImageView = itemView!!.findViewById(R.id.article_img)
  private val dateArticle : TextView = itemView!!.findViewById(R.id.article_date)
  private val titleArticle : TextView = itemView!!.findViewById(R.id.article_title)
  private val descriptionArticle : TextView = itemView!!.findViewById(R.id.article_description)
  private val checkboxArticle : CheckBox = itemView!!.findViewById(R.id.article_checkbox)
  private val checkboxListener = checkboxListener

  fun updateUI(article : Article, checked : Boolean){
    Glide.with(itemView.context).load(article.urlToImage).into(imageArticle)
    imageArticle.setOnClickListener { WebViewActivity.initWebView(itemView.context, article.url!!) }
    dateArticle.text = replaceDate(article.publishedAt)
    titleArticle.text = article.title
    descriptionArticle.text = article.description
    checkboxArticle.isChecked = checked
    checkboxArticle.setOnCheckedChangeListener { buttonView, isChecked ->
      println(buttonView.isPressed)
      println(isChecked)
      if(buttonView.isPressed && isChecked){
        checkboxListener.addToFavorites(article)
      }else if(buttonView.isPressed && !isChecked){
        checkboxListener.removeFromFavorites(article)
      }
    }
  }

  private fun replaceDate(date : String?) : String{
    return date!!.replace(Regex("[T|Z]"), " ")
  }
}

