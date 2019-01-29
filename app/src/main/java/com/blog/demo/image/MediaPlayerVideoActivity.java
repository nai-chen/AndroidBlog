package com.blog.demo.image;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.blog.demo.R;

import java.io.IOException;

public class MediaPlayerVideoActivity extends Activity implements View.OnClickListener {
    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_media_player_video);
        mSurfaceView = findViewById(R.id.surface_view);
        SurfaceHolder holder = mSurfaceView.getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
//                createMediaPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                stopMediaPlayer();
            }
        });

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
            AssetFileDescriptor fd = getAssets().openFd("video.3gp");
            mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mMediaPlayer.prepare();
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.start();
        } catch (IOException e) {
        }
    }

    private void startMediaPlayer() {
        try {
            mMediaPlayer.start();
        } catch (IllegalStateException e) {
        }
    }

    private void pauseMediaPlayer() {
        try  {
            mMediaPlayer.pause();
        } catch (IllegalStateException e) {
        }
    }

    public void stopMediaPlayer() {
        try {
            mMediaPlayer.stop();
        } catch (IllegalStateException e) {
        }
    }

    private class MediaPlayerListener implements MediaPlayer.OnPreparedListener,
            MediaPlayer.OnCompletionListener {

        @Override
        public void onPrepared(MediaPlayer mp) {
            Toast.makeText(MediaPlayerVideoActivity.this, "onPrepared",
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(MediaPlayerVideoActivity.this, "onCompletion",
                    Toast.LENGTH_LONG).show();
        }
    }


}

