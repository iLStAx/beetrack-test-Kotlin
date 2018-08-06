package com.example.marcosalinas.beetrack_test.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Relation
import com.google.gson.annotations.SerializedName
import org.jetbrains.annotations.NotNull

@Entity(tableName = "article_table")
class Article {

  @PrimaryKey(autoGenerate = true)
  @NotNull
  var id : Int? = null

  /*@SerializedName("source")
  @Relation(parentColumn = "id", entityColumn = "id", entity = Source::class)
  var source : Source? = null*/

  @ColumnInfo(name = "author")
  @SerializedName("author")
  var author : String? = null

  @ColumnInfo(name = "title")
  @SerializedName("title")
  var title : String? = null

  @ColumnInfo(name = "description")
  @SerializedName("description")
  var description : String? = null

  @ColumnInfo(name = "url")
  @SerializedName("url")
  var url : String? = null

  @ColumnInfo(name = "url_to_image")
  @SerializedName("urlToImage")
  var urlToImage : String? = null

  @ColumnInfo(name = "published_at")
  @SerializedName("publishedAt")
  var publishedAt : String? = null
}