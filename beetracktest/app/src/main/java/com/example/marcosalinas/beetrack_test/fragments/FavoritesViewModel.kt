package com.example.marcosalinas.beetrack_test.fragments

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.data.ArticleDao
import com.example.marcosalinas.beetrack_test.data.ArticleRoomDatabase
import android.os.AsyncTask



class FavoritesViewModel(application: Application) : ViewModel() {

  var articleDao : ArticleDao? = null

  init {
    val db = ArticleRoomDatabase.getInstance(application)
    articleDao = db!!.articleDao()
  }

  fun getAllArticles() : LiveData<List<Article>> {
   return articleDao!!.getAllArticles()
  }

  fun addToFavorites(article : Article){ insertAsyncTask(articleDao!!).execute(article)}

  fun removeFromFavorites(article : Article){ removeAsyncTask(articleDao!!).execute(article)}

  class Factory(application: Application) : ViewModelProvider.Factory{
    val app = application

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      return FavoritesViewModel(app) as T
    }
  }

  private class insertAsyncTask internal constructor(private val mAsyncTaskDao: ArticleDao) : AsyncTask<Article, Void, Void>() {

    override fun doInBackground(vararg params: Article): Void? {
      mAsyncTaskDao.insert(params[0])
      return null
    }
  }

  private class removeAsyncTask internal constructor(private val mAsyncTaskDao: ArticleDao) : AsyncTask<Article, Void, Void>() {

    override fun doInBackground(vararg params: Article): Void? {
      mAsyncTaskDao.delete(params[0].title)
      return null
    }
  }
}
