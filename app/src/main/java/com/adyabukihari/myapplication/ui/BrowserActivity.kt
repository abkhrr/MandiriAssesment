package com.adyabukihari.myapplication.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.adyabukihari.myapplication.R
import kotlinx.android.synthetic.main.activity_browser.*

class BrowserActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        val url = intent.getStringExtra("url")

        webView.settings.javaScriptEnabled = true
        webView.overScrollMode = WebView.OVER_SCROLL_NEVER
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility = View.GONE
            }
        }
        webView.loadUrl(url)
        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbarWeb)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home ->{
            finish()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
