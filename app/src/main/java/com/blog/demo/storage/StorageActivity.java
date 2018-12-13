package com.blog.demo.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.blog.demo.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cn on 2017/2/17.
 */

public class StorageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asset);

        TextView tvAsset = (TextView) findViewById(R.id.tv_asset);
        tvAsset.setText(readAssetFile("config.properties"));

        TextView tvRaw = (TextView) findViewById(R.id.tv_raw);
        tvRaw.setText(readRawFile(R.raw.config));

        TextView tvStorage = (TextView) findViewById(R.id.tv_storage);
        tvStorage.setText(Environment.getExternalStorageDirectory().getPath());

        SharedPreferences.Editor editor = getSharedPreferences("storage", MODE_PRIVATE).edit();
        editor.putString("key", "from preference");
        editor.commit();

        TextView tvPreference = (TextView) findViewById(R.id.tv_preference);
        tvPreference.setText(getSharedPreferences("storage", MODE_PRIVATE).getString("key", ""));

        FileOutputStream fos = null;
        try {
            fos = openFileOutput("storage", MODE_APPEND);
            fos.write(new String("from file2").getBytes());
        } catch (IOException e) {
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }

        TextView tvFile = (TextView) findViewById(R.id.tv_file);
        tvFile.setText(readFile("storage"));

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/webview.html");
    }

    private String readAssetFile(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(fileName);
            byte[] buffer = new byte[1024];
            int length = -1;
            StringBuffer strBuffer = new StringBuffer();
            while ((length = inputStream.read(buffer)) != -1) {
                strBuffer.append(new String(buffer, 0, length));
            }
            return strBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return "";
    }

    private String readRawFile(int resId) {
        InputStream inputStream = null;
        try {
            inputStream = getResources().openRawResource(resId);
            byte[] buffer = new byte[1024];
            int length = -1;
            StringBuffer strBuffer = new StringBuffer();
            while ((length = inputStream.read(buffer)) != -1) {
                strBuffer.append(new String(buffer, 0, length));
            }
            return strBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return "";
    }

    private String readFile(String fileName) {
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
            byte[] buffer = new byte[1024];
            int length = -1;
            StringBuffer strBuffer = new StringBuffer();
            while ((length = fis.read(buffer)) != -1) {
                strBuffer.append(new String(buffer, 0, length));
            }
            return strBuffer.toString();
        } catch (IOException e) {
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

}
