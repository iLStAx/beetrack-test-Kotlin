package com.example.marcosalinas.beetrack_test.interfaces

import com.example.marcosalinas.beetrack_test.data.Article

interface CheckboxListener {
  fun addToFavorites(article : Article)
  fun removeFromFavorites(article : Article)
}