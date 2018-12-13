package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cn on 2018/4/11.
 */

public class OkHttpActivity extends Activity implements View.OnClickListener {
    private ImageView mIvOkHttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_okhttp);

        findViewById(R.id.btn_string_request).setOnClickListener(this);
        findViewById(R.id.btn_image_request).setOnClickListener(this);
        mIvOkHttp = (ImageView) findViewById(R.id.iv_okhttp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_string_request:
                requestString();
                break;
            case R.id.btn_image_request:
                requestImage();
                break;
        }
    }

    private void requestString() {
        Request.Builder builder = new Request.Builder().url("http://192.168.7.101:8116/newestinfo/homead?type=5");
        builder.method("GET", null);
        builder.addHeader("User-Agent", "tpz v_1.8.2 (Android 4.1.1)");
        builder.addHeader("x-qfgj-ua", "tpz v_1.8.2 (Android 4.1.1)");
        builder.addHeader("x-qfgj-sid", "");
        builder.addHeader("x-qfgj-uid", "0");
        builder.addHeader("x-qfgj-did", "980E1B3FF65CA3F70565A12CEFE6BB32");
        builder.addHeader("x-qfgj-refer", "sms");
        builder.addHeader("x-qfgj-rid", "1");
        builder.addHeader("x-qfgj-rtime", "" + System.currentTimeMillis());
        builder.addHeader("device-name", "Google Nexus 4 - 4.1.1 - API 16 - 768x1280/Genymotion");
        builder.addHeader("x-qfgj-contentmd5", "");
        builder.addHeader("x-qfgj-signature", "");

        OkHttpClient httpClient = new OkHttpClient();
        Call call = httpClient.newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtil.log("OkHttpActivity", response.body().string());
            }
        });

    }

    private void requestImage() {
        Request.Builder builder = new Request.Builder().url("http://dl.games.sina.com.cn/wcpan/d//redtrading/ad/upload/180328-phpHfiMmx-banner328.png");
        builder.method("GET", null);

        OkHttpClient httpClient = new OkHttpClient();
        Call call = httpClient.newCall(builder.build());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();

                mIvOkHttp.post(new Runnable() {
                    @Override
                    public void run() {
                        mIvOkHttp.setImageBitmap(bitmap);
                    }
                });
            }
        });
    }

}
