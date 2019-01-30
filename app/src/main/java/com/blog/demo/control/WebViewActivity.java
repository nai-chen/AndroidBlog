package com.blog.demo.control;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.blog.demo.R;

public class WebViewActivity extends Activity implements View.OnClickListener {
    private WebView webView;
    private ValueCallback<Uri> mValueCallback;
    private ValueCallback<Uri[]> mFilePathCallback;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_control_web_view);

        findViewById(R.id.btn_load).setOnClickListener(this);
        findViewById(R.id.btn_load_asset).setOnClickListener(this);
        findViewById(R.id.btn_call_javascript).setOnClickListener(this);

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType) {
                openFileChooser(uploadFile);
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> uploadFile, String acceptType,
                        String capture) {
                openFileChooser(uploadFile);
            }

            // For Android > 4.1.1
            public void openFileChooser(ValueCallback<Uri> uploadFile) {
                mValueCallback = uploadFile;
                showFile();
            }

            // For Android > 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
                                             FileChooserParams fileChooserParams) {
                mFilePathCallback = filePathCallback;
                showFile();
                return true;
            }
        });

    }

    private void showFile() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_load) {
            webView.loadUrl("http://www.baidu.com");
        } else if (v.getId() == R.id.btn_load_asset) {
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);

            webView.addJavascriptInterface(new JSInterface(), "jsListener");
            webView.loadUrl("file:///android_asset/web_view.html");
        } else if (v.getId() == R.id.btn_call_javascript) {
            webView.loadUrl("javascript:myFunction()");
        }
    }

    public class JSInterface {
        @JavascriptInterface
        public void onCall() {
            Toast.makeText(WebViewActivity.this, "Hello World", Toast.LENGTH_LONG).show();
        }
    }
}
