package com.example.marcosalinas.beetrack_test.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.marcosalinas.beetrack_test.R

class WebViewActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_web_view)
    val webView = findViewById<WebView>(R.id.web_view)
    val url = intent.getStringExtra(KEY_URL)
    webView.webViewClient = WebViewClient()
    webView.loadUrl(url)
  }

  companion object {
    const val KEY_URL = "KEY_URL"

    fun initWebView(context : Context, url : String){

      val intent = Intent(context, WebViewActivity::class.java)
      intent.putExtra(KEY_URL, url)
      context.startActivity(intent)
    }
  }
}
