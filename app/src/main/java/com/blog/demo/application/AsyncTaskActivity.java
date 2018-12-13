package com.blog.demo.application;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blog.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by cn on 2017/6/26.
 */

public class AsyncTaskActivity extends Activity implements View.OnClickListener {

    private ImageView mIvBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task);

        findViewById(R.id.btn_click).setOnClickListener(this);
        mIvBitmap = (ImageView) findViewById(R.id.iv_bitmap);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_click:
                new BitmapAsyncTask().execute("http://dl.games.sina.com.cn/wcpan/d/a50/homepage_ad/2.png");
                break;
        }
    }

    private class BitmapAsyncTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... params) {
            return download(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mIvBitmap.setImageBitmap(bitmap);
        }
    }

    private Bitmap download(String url) {
        try {
            URL uri = new URL(url);
            URLConnection connection = uri.openConnection();
            InputStream input = connection.getInputStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;

            while ((length = input.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            input.close();

            return BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size());
        } catch (IOException e) {
        }

        return null;
    }

}
