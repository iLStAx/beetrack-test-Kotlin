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
import com.example.marcosalinas.beetrack_test.adapters.FavoritesAdapter
import com.example.marcosalinas.beetrack_test.data.Article
import com.example.marcosalinas.beetrack_test.interfaces.CheckboxListener

class FavoritesFragment : Fragment(), CheckboxListener {

  override fun addToFavorites(article: Article) {
  }

  override fun removeFromFavorites(article: Article) {
    favoritesViewModel.removeFromFavorites(article)
    toast.setText("Article was removed from favorites")
    toast.duration = Toast.LENGTH_SHORT
    toast.show()
  }

  private lateinit var favoritesViewModel: FavoritesViewModel
  private lateinit var adapter : FavoritesAdapter
  private lateinit var toast: Toast


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.favorites_fragment, container, false)
    val recyclerView : RecyclerView = view.findViewById(R.id.recycler_view)
    toast = Toast(context)
    adapter = FavoritesAdapter(this)
    recyclerView.adapter  = adapter
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val factory  = FavoritesViewModel.Factory(activity!!.application)
    favoritesViewModel = ViewModelProviders.of(this, factory).get(FavoritesViewModel::class.java)
    favoritesViewModel.getAllArticles().observe(this, Observer {articles -> adapter.setArticles(articles!!) })
  }

}
