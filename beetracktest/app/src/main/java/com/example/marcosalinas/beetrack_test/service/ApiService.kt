package com.example.marcosalinas.beetrack_test.service

import com.example.marcosalinas.beetrack_test.data.ApiResponse
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiService {
  @GET("/v1/articles?source=the-verge&apiKey=0c82a2269efd4a3cadb5d916b533b38b")
  fun getApiResponse() : Call<JsonElement>

  companion object Factory{
    val BASE_URL = "https://newsapi.org/"
    fun create() : ApiService {
      val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
      return retrofit.create(ApiService::class.java)
    }
  }
}