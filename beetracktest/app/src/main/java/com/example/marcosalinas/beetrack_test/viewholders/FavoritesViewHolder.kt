package com.example.marcosalinas.beetrack_test.viewholders

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.activities.WebViewActivity
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener

class FavoritesViewHolder(itemView: View?, checkboxListener: CheckboxListener) : RecyclerView.ViewHolder(itemView) {

  private val imageArticle : ImageView = itemView!!.findViewById(R.id.article_img)
  private val dateArticle : TextView = itemView!!.findViewById(R.id.article_date)
  private val titleArticle : TextView = itemView!!.findViewById(R.id.article_title)
  private val descriptionArticle : TextView = itemView!!.findViewById(R.id.article_description)
  private val checkboxArticle : CheckBox = itemView!!.findViewById(R.id.article_checkbox)
  private val buttonArticle : Button = itemView!!.findViewById(R.id.article_button)
  private val checkboxListener = checkboxListener

  fun updateUI(article : Article){
    Glide.with(itemView.context).load(article.urlToImage).into(imageArticle)
    imageArticle.setOnClickListener { WebViewActivity.initWebView(itemView.context, article.url!!) }
    dateArticle.text = replaceDate(article.publishedAt)
    titleArticle.text = article.title
    descriptionArticle.text = article.description
    checkboxArticle.visibility = View.GONE
    buttonArticle.visibility = View.VISIBLE
    buttonArticle.setOnClickListener { setConfirmDialog(article) }
  }

  private fun replaceDate(date : String?) : String{
    return date!!.replace(Regex("[T|Z]"), " ")
  }

  private fun setConfirmDialog(article : Article){
    val dialog = AlertDialog.Builder(itemView.context)
    dialog.setTitle(R.string.are_you_sure)
    dialog.setNegativeButton(R.string.close) { _, _ -> }
    dialog.setPositiveButton(R.string.accept) { _, _ -> checkboxListener.removeFromFavorites(article) }
    dialog.show()
  }
}