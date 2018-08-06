package com.example.marcosalinas.beetrack_test.activities

import android.support.v7.app.AppCompatActivity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.example.marcosalinas.beetrack_test.R
import com.example.marcosalinas.beetrack_test.fragments.FavoritesFragment
import com.example.marcosalinas.beetrack_test.fragments.MainFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  private val MAIN_FRAGMENT = "MAIN_FRAGMENT"
  private val FAVORITES_FRAGMENT = "FAVORITES_FRAGMENT"

  private var mBottomNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
    when(it.itemId){
      R.id.news -> replaceMainFragment(MainFragment())
      R.id.favorites -> replaceFavoriteFragment(FavoritesFragment())
      else -> replaceMainFragment(MainFragment())
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val mf = MainFragment()
    supportFragmentManager.beginTransaction().add(R.id.container, mf, MAIN_FRAGMENT).commit()
    bottom_navigation.setOnNavigationItemSelectedListener(mBottomNavigationListener)
  }

  private fun replaceMainFragment(mf : MainFragment) : Boolean{
    supportFragmentManager.beginTransaction().addToBackStack(MAIN_FRAGMENT).replace(R.id.container, mf, MAIN_FRAGMENT).commit()
    return true
  }

  private fun replaceFavoriteFragment(ff : FavoritesFragment) : Boolean{
    supportFragmentManager.beginTransaction().addToBackStack(FAVORITES_FRAGMENT).replace(R.id.container, ff,FAVORITES_FRAGMENT).commit()
    return true
  }
}
