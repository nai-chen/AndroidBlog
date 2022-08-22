package com.blog.demo.image

import android.app.Activity
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.media.MediaPlayer.OnPreparedListener
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.blog.demo.LogTool
import com.blog.demo.R
import java.io.IOException

class MediaPlayerAudioActivity : Activity(), View.OnClickListener {
    private val LOG_TAG = "MediaPlayerAudioActivity"

    private var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_media_player_audio)

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
        mMediaPlayer = null
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
        mMediaPlayer?.reset()
        try {
            val fd = assets.openFd("demo.mp3")
            mMediaPlayer?.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
            mMediaPlayer?.prepare()
            mMediaPlayer?.start()
        } catch (e: IOException) {
            LogTool.loge(LOG_TAG, e)
        }
    }

    private fun startMediaPlayer() {
        try {
            mMediaPlayer?.start()
        } catch (e: IllegalStateException) {
            LogTool.loge(LOG_TAG, e)
        }
    }

    private fun pauseMediaPlayer() {
        try {
            mMediaPlayer?.pause()
        } catch (e: IllegalStateException) {
            LogTool.loge(LOG_TAG, e)
        }
    }

    private fun stopMediaPlayer() {
        try {
            mMediaPlayer?.stop()
        } catch (e: IllegalStateException) {
            LogTool.loge(LOG_TAG, e)
        }
    }

    private inner class MediaPlayerListener : OnPreparedListener, OnCompletionListener {

        override fun onPrepared(mp: MediaPlayer) {
            Toast.makeText(this@MediaPlayerAudioActivity, "onPrepared", Toast.LENGTH_LONG).show()
        }

        override fun onCompletion(mp: MediaPlayer) {
            Toast.makeText(this@MediaPlayerAudioActivity, "onCompletion", Toast.LENGTH_LONG).show()
        }

    }

}