package com.blog.demo.image

import android.app.Activity
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.LogTool
import com.blog.demo.R
import java.io.IOException

class MediaMetadataRetrieverActivity : Activity(), View.OnClickListener {

    private val LOG_TAG = "MediaMetadataRetrieverActivity"
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_media_meta_data_retriever)

        findViewById<Button>(R.id.btn_get_audio_info).setOnClickListener(this)
        findViewById<Button>(R.id.btn_get_video_info).setOnClickListener(this)

        imageView = findViewById(R.id.image_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_get_video_info) {
            val retriever = MediaMetadataRetriever()
            try {
                val fd = assets.openFd("video.3gp")
                retriever.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
                val bitmap = retriever.frameAtTime // 视频第一帧图像
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)
                    LogTool.logi(LOG_TAG, bitmap.width.toString() + "")
                    LogTool.logi(LOG_TAG, bitmap.height.toString() + "")
                }
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT))
            } catch (e: IOException) {
                LogTool.loge(LOG_TAG, e)
            }
        } else if (v.id == R.id.btn_get_audio_info) {
            val retriever = MediaMetadataRetriever()
            try {
                val fd = assets.openFd("demo.mp3")
                retriever.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE))
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE))
            } catch (e: IOException) {
                LogTool.loge(LOG_TAG, e)
            }
        }
    }

}