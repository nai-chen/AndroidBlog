package com.blog.demo.image;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.io.FileDescriptor;
import java.io.IOException;

public class MediaPlayerAudioActivity extends Activity implements View.OnClickListener {
    private static final String LOG_TAG = "MediaPlayerAudioActivity";
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_media_player_audio);

        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);

        mMediaPlayer = new MediaPlayer();
        MediaPlayerListener listener = new MediaPlayerListener();
        mMediaPlayer.setOnPreparedListener(listener);
        mMediaPlayer.setOnCompletionListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create:
                createMediaPlayer();
            case R.id.btn_start:
                startMediaPlayer();
                break;
            case R.id.btn_pause:
                pauseMediaPlayer();
                break;
            case R.id.btn_stop:
                stopMediaPlayer();
                break;
        }
    }

    private void createMediaPlayer() {
        mMediaPlayer.reset();

        try {
            AssetFileDescriptor fd = getAssets().openFd("demo.mp3");
            mMediaPlayer.setDataSource(fd.getFileDescriptor(),
                    fd.getStartOffset(), fd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            LogTool.loge(LOG_TAG, e);
        }
    }

    private void startMediaPlayer() {
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            LogTool.loge(LOG_TAG, e);
        }
    }

    private void pauseMediaPlayer() {
        try  {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            LogTool.loge(LOG_TAG, e);
        }
    }

    public void stopMediaPlayer() {
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            LogTool.loge(LOG_TAG, e);
        }
    }

    private class MediaPlayerListener implements MediaPlayer.OnPreparedListener,
            MediaPlayer.OnCompletionListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Toast.makeText(MediaPlayerAudioActivity.this, "onPrepared",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(MediaPlayerAudioActivity.this, "onCompletion",
                    Toast.LENGTH_LONG).show();
        }
    }
}


