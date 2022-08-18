package com.blog.demo.feature;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class AsyncTaskActivity extends Activity {

    private ProgressBar mProgressBar;
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_async_task);

        findViewById(R.id.btn_download).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new BitmapAsyncTask().execute("http://img.netbian.com/file/2019/0115/cc7cf9dad4e841b58a76a1d8ad594209.jpg");
            }
        });

        mProgressBar = findViewById(R.id.progress_bar_download);
        mImageView = findViewById(R.id.iv_down_load);
    }

    private class BitmapAsyncTask extends AsyncTask<String, Integer, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            try {
                URL uri = new URL(url[0]);
                URLConnection connection = uri.openConnection();
                InputStream input = connection.getInputStream();
                int contentLength = connection.getContentLength();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                byte[] buffer = new byte[1024];
                int length, sum = 0;

                while ((length = input.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                    sum += length;
                    publishProgress(sum * 100 / contentLength);
                    LogTool.logi("AsyncTaskActivity", "sum = " + sum + ", contentLength = " + contentLength);
                }
                input.close();

                return BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }
    }

}
