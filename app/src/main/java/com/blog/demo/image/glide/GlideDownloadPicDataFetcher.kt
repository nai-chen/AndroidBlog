package com.blog.demo.image.glide

import android.util.Base64
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.data.DataFetcher
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okhttp3.Response
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.InputStream

class GlideDownloadPicDataFetcher(downloadPic: GlideDownloadPic) : DataFetcher<InputStream> {

    private var mDownloadPic = downloadPic

    override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in InputStream>) {
        downloadPic(mDownloadPic.picUrl, {
            callback.onDataReady(ByteArrayInputStream(Base64.decode(it, Base64.DEFAULT)))
        }, {
            callback.onLoadFailed(it)
        })
    }

    // 下载图片
    private fun downloadPic(picUrl: String, onSuccess:(ByteArray) -> Unit, onFail:(Exception) -> Unit) {
        if (picUrl.startsWith("http")) {
            NetworkUtil.getOKHttpClient()
                .newCall(Request.Builder().url(picUrl).build())
                .enqueue(object : Callback{
                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) {
                            var bodyByteArray = response.body()?.bytes()
                            if (bodyByteArray != null) {
                                onSuccess(bodyByteArray)
                                return
                            }
                        }
                        onFail(IOException("wrong url $picUrl"))
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        onFail(IOException("wrong url $picUrl"))
                    }
                })
        } else {
            onFail(IOException("wrong url $picUrl"))
        }
    }

    override fun getDataClass(): Class<InputStream> {
        return InputStream::class.java
    }

    override fun cleanup() {
    }

    override fun getDataSource(): DataSource {
        return DataSource.REMOTE
    }

    override fun cancel() {
    }
}