package com.example.marcosalinas.beetrack_test.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArticleDao {

  @Insert
  fun insert(article : Article?)

  /*@Delete work if the object is the same in db
  fun delete(article : Article?)*/

  @Query("DELETE from article_table where title = :title")
  fun delete(title : String?)

  @Query("SELECT * from article_table ORDER BY id DESC")
  fun getAllArticles(): LiveData<List<Article>>
}