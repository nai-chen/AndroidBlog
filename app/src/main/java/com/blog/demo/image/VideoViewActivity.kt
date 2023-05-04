package com.blog.demo.image

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import com.blog.demo.R

class VideoViewActivity : Activity(), View.OnClickListener {
    private lateinit var mVideoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video_view)

        mVideoView = findViewById(R.id.video_view)

        var uri = "android.resource://com.blog.demo/${R.raw.video_demo}"
        mVideoView.setVideoURI(Uri.parse(uri))
        var mc = MediaController(this)
        mc.setPrevNextListeners({
            Log.i("VideoViewActivity", "Next")
        }, {
            Log.i("VideoViewActivity", "Prev")
        })
        mVideoView.setMediaController(mc)
        mVideoView.start()

        findViewById<Button>(R.id.btn_resume).setOnClickListener(this)
        findViewById<Button>(R.id.btn_start).setOnClickListener(this)
        findViewById<Button>(R.id.btn_pause).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop).setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mVideoView.suspend()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_resume -> {
                resumeVideo()
            }
            R.id.btn_start -> {
                startVideo()
            }
            R.id.btn_pause -> {
                pauseVideo()
            }
            R.id.btn_stop -> {
                stopVideo()
            }
        }
    }

    private fun resumeVideo() {
        mVideoView.resume()
    }

    private fun startVideo() {
        mVideoView.start()
    }

    private fun pauseVideo() {
        mVideoView.pause()
    }

    private fun stopVideo() {
        mVideoView.stopPlayback()
    }

}