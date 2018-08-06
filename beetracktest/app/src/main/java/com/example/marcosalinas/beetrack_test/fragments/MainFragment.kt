package com.example.marcosalinas.beetrack_test.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.adapters.NewsAdapter
import com.example.marcosalinas.beetrack_test.data.ApiResponse
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener
import com.example.marcosalinas.beetrack_test.service.ApiService
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainFragment : Fragment(), CheckboxListener {

  override fun addToFavorites(article: Article) {
    favoritesViewModel.addToFavorites(article)
    Toast.makeText(context, "Article was added to favorites", Toast.LENGTH_LONG).show()
  }

  override fun removeFromFavorites(article: Article) {
    favoritesViewModel.removeFromFavorites(article)
    //Toast.makeText(context, "Article ${article.title} removed from favorites-", Toast.LENGTH_LONG).show()
    Toast.makeText(context, "Article was removed from favorites", Toast.LENGTH_LONG).show()
  }

  private lateinit var adapter: NewsAdapter
  private lateinit var favoritesViewModel : FavoritesViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view : View = inflater.inflate(R.layout.main_fragment, container, false)
    val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
    adapter = NewsAdapter(this)
    recyclerView.adapter = adapter
    getArticles()
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val factory = FavoritesViewModel.Factory(activity!!.application)
    favoritesViewModel = ViewModelProviders.of(this, factory).get(FavoritesViewModel::class.java)
    favoritesViewModel.getAllArticles().observe(this, Observer { articles ->     adapter.setFavArticles(articles!!)})
  }


  private fun getArticles() {
    val apiService = ApiService.create()
    val call = apiService.getApiResponse()
    call.enqueue(object : Callback, retrofit2.Callback<JsonElement> {
      override fun onResponse(call: Call<JsonElement>?, response: Response<JsonElement>?) {
        val apiResponse = Gson().fromJson(response!!.body(), ApiResponse::class.java)
        adapter.setArticles(apiResponse.articles!!)
        progress_bar.visibility = View.GONE
      }

      override fun onFailure(call: Call<JsonElement>?, t: Throwable?) {
      }
    })
  }
}
