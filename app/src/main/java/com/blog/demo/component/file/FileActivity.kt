package com.blog.demo.component.file

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.blog.demo.R
import java.io.IOException

class FileActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_component_file_file)
        val tvAsset: TextView = findViewById(R.id.tv_asset)
        try {
            assets.open("config.properties").use { inputStream ->
                val buffer = ByteArray(1024)
                val length = inputStream.read(buffer)
                tvAsset.text = String(buffer, 0, length)
            }
        } catch (e: IOException) {
        }
        val tvRaw: TextView = findViewById(R.id.tv_raw)
        try {
            resources.openRawResource(R.raw.config).use { inputStream ->
                val buffer = ByteArray(1024)
                val length = inputStream.read(buffer)
                tvRaw.text = String(buffer, 0, length)
            }
        } catch (e: Exception) {
        }

        val webView: WebView = findViewById(R.id.web_view)
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()
        webView.loadUrl("file:///android_asset/webview.html")
    }
}