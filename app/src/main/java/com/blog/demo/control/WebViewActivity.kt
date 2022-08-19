package com.blog.demo.control

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.*
import android.widget.Button
import android.widget.Toast
import com.blog.demo.R

class WebViewActivity : Activity(), View.OnClickListener {

    private lateinit var webView: WebView
    private var mValueCallback: ValueCallback<Uri?>? = null
    private var mFilePathCallback: ValueCallback<Array<Uri>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_web_view)

        findViewById<Button>(R.id.btn_load).setOnClickListener(this)
        findViewById<Button>(R.id.btn_load_asset).setOnClickListener(this)
        findViewById<Button>(R.id.btn_call_javascript).setOnClickListener(this)

        webView = findViewById(R.id.web_view)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                webView.loadUrl(url)
                return true
            }
        }

        webView.webChromeClient = object : WebChromeClient() {
            // For Android 3.0+
            fun openFileChooser(uploadFile: ValueCallback<Uri?>?, acceptType: String?) {
                openFileChooser(uploadFile)
            }

            // For Android < 3.0
            fun openFileChooser(
                uploadFile: ValueCallback<Uri?>?, acceptType: String?,
                capture: String?
            ) {
                openFileChooser(uploadFile)
            }

            // For Android > 4.1.1
            fun openFileChooser(uploadFile: ValueCallback<Uri?>?) {
                mValueCallback = uploadFile
                showFile()
            }

            // For Android > 5.0
            override fun onShowFileChooser(
                webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
                fileChooserParams: FileChooserParams
            ): Boolean {
                mFilePathCallback = filePathCallback
                showFile()
                return true
            }
        }
        webView.loadUrl("http://www.baidu.com")
    }

    private fun showFile() {
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_load) {
            webView.loadUrl("http://www.sohu.com")
        } else if (v.id == R.id.btn_load_asset) {
            val settings = webView.settings
            settings.javaScriptEnabled = true
            webView.addJavascriptInterface(JSInterface(), "jsListener")
            webView.loadUrl("file:///android_asset/web_view.html")
        } else if (v.id == R.id.btn_call_javascript) {
            webView.loadUrl("javascript:myFunction()")
        }
    }

    private inner class JSInterface {
        @JavascriptInterface
        fun onCall() {
            Toast.makeText(this@WebViewActivity, "Hello World", Toast.LENGTH_LONG).show()
        }
    }

}