package com.blog.demo.feature

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.blog.demo.LogTool.logi
import com.blog.demo.R
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.net.URL

class AsyncTaskActivity : Activity() {

    private lateinit var mProgressBar: ProgressBar
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_feature_async_task)

        mProgressBar = findViewById(R.id.progress_bar_download)
        mImageView = findViewById(R.id.iv_down_load)

        findViewById<Button>(R.id.btn_download).setOnClickListener {
            BitmapAsyncTask().execute(
                "http://img.netbian.com/file/2019/0115/cc7cf9dad4e841b58a76a1d8ad594209.jpg"
            )
        }
    }

    private inner class BitmapAsyncTask : AsyncTask<String, Int, Bitmap?>() {

        override fun doInBackground(vararg url: String): Bitmap? {
            try {
                val uri = URL(url[0])
                val connection = uri.openConnection()
                val input = connection.getInputStream()
                val contentLength = connection.contentLength
                val outputStream = ByteArrayOutputStream()
                val buffer = ByteArray(1024)
                var length: Int
                var sum = 0
                while (input.read(buffer).also { length = it } != -1) {
                    outputStream.write(buffer, 0, length)
                    sum += length
                    publishProgress(sum * 100 / contentLength)
                    logi("AsyncTaskActivity", "sum = $sum, contentLength = $contentLength")
                }
                input.close()
                return BitmapFactory.decodeByteArray(
                    outputStream.toByteArray(),
                    0,
                    outputStream.size()
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            mProgressBar.progress = values.getOrNull(0) ?: 0
        }

        override fun onPostExecute(bitmap: Bitmap?) {
            mImageView.setImageBitmap(bitmap)
        }

    }

}