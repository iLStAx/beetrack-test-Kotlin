package com.example.marcosalinas.beetrack_test.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "source_table")
class Source {

  @PrimaryKey
  @SerializedName("id")
  @ColumnInfo(name ="id")
  var id : String? = null

  @SerializedName("name")
  @ColumnInfo(name = "name")
  var name : String? = null

}