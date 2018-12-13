package com.blog.demo.media;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;
import com.blog.demo.Util;

import java.io.IOException;

/**
 * Created by cn on 2017/4/13.
 */

public class MediaPlayerAudioActivity extends Activity implements View.OnClickListener {

    private MediaPlayer mMediaPlayer;

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media_player_audio);

        findViewById(R.id.btn_create).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
        findViewById(R.id.btn_get_position).setOnClickListener(this);
        findViewById(R.id.btn_seek_to).setOnClickListener(this);

        mMediaPlayer = new MediaPlayer();
        MediaPlayerListener listener = new MediaPlayerListener();
        mMediaPlayer.setOnPreparedListener(listener);
        mMediaPlayer.setOnCompletionListener(listener);
        mMediaPlayer.setOnSeekCompleteListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
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
            case R.id.btn_get_position:
                try {
                    mPosition = mMediaPlayer.getCurrentPosition();
                } catch (IllegalStateException e) {
                    LogUtil.log("MediaPlayerAudioActivity", "getCurrentPosition IllegalStateException");
                }
                break;
            case R.id.btn_seek_to:
                try {
                    mMediaPlayer.seekTo(mPosition);
                } catch (IllegalStateException e) {
                    LogUtil.log("MediaPlayerAudioActivity", "seekTo IllegalStateException");
                }
                break;
        }
    }

    private void createMediaPlayer() {
        mMediaPlayer.reset();
        String filePath = Util.copyFromAsset(this, "demo.mp3");
        LogUtil.log("MediaPlayerAudioActivity filePath = ", filePath);

        try {
            LogUtil.log("MediaPlayerAudioActivity", "setDataSource");
            mMediaPlayer.setDataSource(filePath);
//            mMediaPlayer.prepareAsync();
            LogUtil.log("MediaPlayerAudioActivity", "prepare");
            mMediaPlayer.prepare();
            LogUtil.log("MediaPlayerAudioActivity", "start");
            mMediaPlayer.start();
        } catch (IOException e) {
            Toast.makeText(this, "create file", Toast.LENGTH_LONG).show();
            LogUtil.log("MediaPlayerAudioActivity", e.getMessage());
        }

    }

    private void startMediaPlayer() {
        LogUtil.log("MediaPlayerAudioActivity", "startMediaPlayer");
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
            LogUtil.log("MediaPlayerAudioActivity", "start IllegalStateException");
        }
    }

    private void pauseMediaPlayer() {
        LogUtil.log("MediaPlayerAudioActivity", "pauseMediaPlayer");
        try  {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            LogUtil.log("MediaPlayerAudioActivity", "pause IllegalStateException");
        }
    }

    public void stopMediaPlayer() {
        LogUtil.log("MediaPlayerAudioActivity", "stopMediaPlayer");
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            LogUtil.log("MediaPlayerAudioActivity", "stop IllegalStateException");
        }

    }

    private class MediaPlayerListener implements MediaPlayer.OnPreparedListener,
            MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            LogUtil.log("MediaPlayerAudioActivity", "onPrepared");
            startMediaPlayer();
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            LogUtil.log("MediaPlayerAudioActivity", "onCompletion");
            stopMediaPlayer();
        }

        @Override
        public void onSeekComplete(MediaPlayer mp) {
            LogUtil.log("MediaPlayerAudioActivity", "onSeekComplete");
        }
    }



}
