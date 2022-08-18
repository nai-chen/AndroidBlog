package com.blog.demo.feature.storage;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blog.demo.R;

import java.io.IOException;
import java.io.InputStream;

public class FileActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feature_storage_file);

        TextView tvAsset = findViewById(R.id.tv_asset);

        try (InputStream inputStream = getAssets().open("config.properties")) {
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            tvAsset.setText(new String(buffer, 0, length));
        } catch (IOException e) {
        }

        TextView tvRaw = findViewById(R.id.tv_raw);

        try (InputStream inputStream = getResources().openRawResource(R.raw.config)) {
            byte[] buffer = new byte[1024];
            int length = inputStream.read(buffer);
            tvRaw.setText(new String(buffer, 0, length));
        } catch (Exception e) {
        }

        WebView webView = findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/webview.html");
    }

}
