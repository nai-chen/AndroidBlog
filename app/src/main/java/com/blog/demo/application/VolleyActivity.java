package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cn on 2018/4/10.
 */

public class VolleyActivity extends Activity implements View.OnClickListener {
    private ImageView mIvVolley;

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volley);

        findViewById(R.id.btn_string_request).setOnClickListener(this);
        findViewById(R.id.btn_image_request).setOnClickListener(this);
        mIvVolley = (ImageView) findViewById(R.id.iv_volley);

        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
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
        mRequestQueue.add(new StringRequest(Request.Method.GET,
                "http://192.168.7.101:8116/newestinfo/homead?type=5",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LogUtil.log("VolleyActivity", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", "tpz v_1.8.2 (Android 4.1.1)");
                header.put("x-qfgj-ua", "tpz v_1.8.2 (Android 4.1.1)");
                header.put("x-qfgj-sid", "");
                header.put("x-qfgj-uid", "0");
                header.put("x-qfgj-did", "980E1B3FF65CA3F70565A12CEFE6BB32");
                header.put("x-qfgj-refer", "sms");
                header.put("x-qfgj-rid", "1");
                header.put("x-qfgj-rtime", "" + System.currentTimeMillis());
                header.put("device-name", "Google Nexus 4 - 4.1.1 - API 16 - 768x1280/Genymotion");
                header.put("x-qfgj-contentmd5", "");
                header.put("x-qfgj-signature", "");

                return header;
            }
        });
    }

    private void requestImage() {
        mRequestQueue.add(new ImageRequest("http://dl.games.sina.com.cn/wcpan/d//redtrading/ad/upload/180328-phpHfiMmx-banner328.png",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        mIvVolley.setImageBitmap(response);
                    }
                }, 750, 320, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

}
