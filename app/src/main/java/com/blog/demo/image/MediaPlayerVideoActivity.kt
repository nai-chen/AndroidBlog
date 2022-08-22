package com.blog.demo.image

import android.app.Activity
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.blog.demo.R
import java.io.IOException

class MediaPlayerVideoActivity : Activity(), View.OnClickListener {

    private var mMediaPlayer: MediaPlayer? = null
    private lateinit var mSurfaceView: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_media_player_video)

        mSurfaceView = findViewById(R.id.surface_view)

        val holder = mSurfaceView.holder
        holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                // createMediaPlayer方法必须要等待Surface被创建以后调用
                createMediaPlayer()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })

        findViewById<Button>(R.id.btn_create).setOnClickListener(this)
        findViewById<Button>(R.id.btn_start).setOnClickListener(this)
        findViewById<Button>(R.id.btn_pause).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop).setOnClickListener(this)

        mMediaPlayer = MediaPlayer()

        val listener = MediaPlayerListener()
        mMediaPlayer?.setOnPreparedListener(listener)
        mMediaPlayer?.setOnCompletionListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        mMediaPlayer?.release()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_create -> {
                createMediaPlayer()
                startMediaPlayer()
            }
            R.id.btn_start -> {
                startMediaPlayer()
            }
            R.id.btn_pause -> {
                pauseMediaPlayer()
            }
            R.id.btn_stop -> {
                stopMediaPlayer()
            }
        }
    }

    private fun createMediaPlayer() {
        try {
            mMediaPlayer?.reset()

            val fd = assets.openFd("video.3gp")
            mMediaPlayer?.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            mMediaPlayer?.prepare()
            mMediaPlayer?.setDisplay(mSurfaceView.holder)
            mMediaPlayer?.start()
        } catch (e: IOException) {
        }
    }

    private fun startMediaPlayer() {
        try {
            mMediaPlayer?.start()
        } catch (e: IllegalStateException) {
        }
    }

    private fun pauseMediaPlayer() {
        try {
            mMediaPlayer?.pause()
        } catch (e: IllegalStateException) {
        }
    }

    private fun stopMediaPlayer() {
        try {
            mMediaPlayer?.stop()
        } catch (e: IllegalStateException) {
        }
    }

    private inner class MediaPlayerListener : OnPreparedListener, OnCompletionListener {

        override fun onPrepared(mp: MediaPlayer) {
            Toast.makeText(this@MediaPlayerVideoActivity, "onPrepared", Toast.LENGTH_LONG).show()
        }

        override fun onCompletion(mp: MediaPlayer) {
            Toast.makeText(this@MediaPlayerVideoActivity, "onCompletion", Toast.LENGTH_LONG).show()
        }

    }

}