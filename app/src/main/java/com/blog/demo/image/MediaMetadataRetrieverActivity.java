package com.blog.demo.image;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.io.IOException;

public class MediaMetadataRetrieverActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "MediaMetadataRetrieverActivity";
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_media_meta_data_retriever);
        findViewById(R.id.btn_get_audio_info).setOnClickListener(this);
        findViewById(R.id.btn_get_video_info).setOnClickListener(this);

        imageView = findViewById(R.id.image_view);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_get_video_info) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                AssetFileDescriptor fd = getAssets().openFd("video.3gp");
                retriever.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                Bitmap bitmap = retriever.getFrameAtTime(); // 视频第一帧图像
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    LogTool.logi(LOG_TAG, bitmap.getWidth() + "");
                    LogTool.logi(LOG_TAG, bitmap.getHeight() + "");
                }
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
            } catch (IOException e) {
                LogTool.loge(LOG_TAG, e);
            }
        } else if (v.getId() == R.id.btn_get_audio_info) {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            try {
                AssetFileDescriptor fd = getAssets().openFd("demo.mp3");
                retriever.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE) + "");
                LogTool.logi(LOG_TAG, retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE) + "");
            } catch (IOException e) {
                LogTool.loge(LOG_TAG, e);
            }
        }
    }
}
