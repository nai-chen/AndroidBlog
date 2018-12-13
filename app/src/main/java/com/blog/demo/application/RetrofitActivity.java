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
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by cn on 2018/4/11.
 */

public class RetrofitActivity extends Activity implements View.OnClickListener {
    private ImageView mIvRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.btn_string_request).setOnClickListener(this);
        findViewById(R.id.btn_image_request).setOnClickListener(this);
        mIvRetrofit = (ImageView) findViewById(R.id.iv_retrofit);
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.7.101:8116/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IHomeAdService homeAdService = retrofit.create(IHomeAdService.class);
        Call<HomeAd> call = homeAdService.getHomeAd();
        call.enqueue(new Callback<HomeAd>() {
            @Override
            public void onResponse(Call<HomeAd> call, Response<HomeAd> response) {
                HomeAd homeAd = response.body();
                if (homeAd != null && homeAd.records != null && homeAd.records.size() > 0) {
                    LogUtil.log("RetrofitActivity", homeAd.records.get(0).pic);
                }
            }

            @Override
            public void onFailure(Call<HomeAd> call, Throwable t) {

            }
        });
    }

    private void requestImage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dl.games.sina.com.cn/")
                .build();
        IPicService service = retrofit.create(IPicService.class);
        Call<ResponseBody> responseBodyCall = service.downloadPic();
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    InputStream inputStream = response.body().byteStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    inputStream.close();

                    mIvRetrofit.post(new Runnable() {
                        @Override
                        public void run() {
                            mIvRetrofit.setImageBitmap(bitmap);
                        }
                    });
                } catch (IOException e) {
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public interface IHomeAdService {
        @GET("newestinfo/homead?type=5")
        @Headers({"User-Agent: tpz v_1.8.2 (Android 4.1.1)",
                "x-qfgj-ua: tpz v_1.8.2 (Android 4.1.1)",
        "x-qfgj-sid: ",
        "x-qfgj-uid: 0",
        "x-qfgj-did: 980E1B3FF65CA3F70565A12CEFE6BB32",
        "x-qfgj-refer: sms",
        "x-qfgj-rid: 1",
        "device-name: Google Nexus 4 - 4.1.1 - API 16 - 768x1280/Genymotion",
        "x-qfgj-contentmd5: ",
        "x-qfgj-signature: "})
        Call<HomeAd> getHomeAd();
    }

    public static class HomeAd {
        public List<HomePage> records;
    }

    public static class HomePage {
        public int id;
        public String pic;
    }

    public interface IPicService {
        @GET("wcpan/d//redtrading/ad/upload/180328-phpHfiMmx-banner328.png")
        Call<ResponseBody> downloadPic();
    }

}
