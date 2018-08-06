package com.example.marcosalinas.beetrack_test.data

import com.google.gson.annotations.SerializedName

class ApiResponse{

  @SerializedName("status")
  var status: String? = null

  @SerializedName("totalResults")
  var totalResults: Int? = 0

  @SerializedName("articles")
  var articles : ArrayList<Article>? = null
}