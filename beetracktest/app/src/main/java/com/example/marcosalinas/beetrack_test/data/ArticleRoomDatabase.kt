package com.example.marcosalinas.beetrack_test.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(Article::class)], version = 1)
abstract class ArticleRoomDatabase : RoomDatabase() {
  abstract fun articleDao() : ArticleDao

  companion object {
    private var INSTANCE: ArticleRoomDatabase? = null

    fun getInstance(context : Context): ArticleRoomDatabase? {
      if (INSTANCE == null) {
        synchronized(ArticleRoomDatabase::class) {
          INSTANCE = Room.databaseBuilder(context.applicationContext, ArticleRoomDatabase::class.java, "article.db").build()
        }
      }
      return INSTANCE
    }

    fun destroyInstance(){ INSTANCE = null }
  }
}