package com.blog.demo.third.exoplayer

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class ExoplayerNormalActivity  : Activity(), View.OnClickListener {

    private lateinit var mExoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_exoplayer_normal)

        var playerView: StyledPlayerView = findViewById(R.id.styled_player_view)
        mExoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = mExoPlayer

        var uri = "android.resource://com.blog.demo/${R.raw.video_demo}"
        mExoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(uri)))
        mExoPlayer.prepare()
        mExoPlayer.play()

        findViewById<Button>(R.id.btn_resume).setOnClickListener(this)
        findViewById<Button>(R.id.btn_start).setOnClickListener(this)
        findViewById<Button>(R.id.btn_pause).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop).setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mExoPlayer.release()
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
        mExoPlayer.prepare()
        mExoPlayer.play()
    }

    private fun startVideo() {
        mExoPlayer.play()
    }

    private fun pauseVideo() {
        mExoPlayer.pause()
    }

    private fun stopVideo() {
        mExoPlayer.stop()
    }

}