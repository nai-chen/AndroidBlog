package com.blog.demo.image.exoplayer

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.blog.demo.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView

class ExoplayerMediaItemActivity  : Activity(), View.OnClickListener {

    private lateinit var mExoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_exoplayer_media_item)

        var playerView: StyledPlayerView = findViewById(R.id.styled_player_view)
        mExoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = mExoPlayer

        var uri = "android.resource://com.blog.demo/${R.raw.video_demo}"
        mExoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(uri)))
        var nextUri = "android.resource://com.blog.demo/${R.raw.video_demo2}"
        mExoPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(nextUri)))
        mExoPlayer.prepare()
        mExoPlayer.play()

        findViewById<Button>(R.id.btn_mode_off).setOnClickListener(this)
        findViewById<Button>(R.id.btn_mode_one).setOnClickListener(this)
        findViewById<Button>(R.id.btn_mode_all).setOnClickListener(this)

        findViewById<Button>(R.id.btn_seek_back).setOnClickListener(this)
        findViewById<Button>(R.id.btn_seek_forward).setOnClickListener(this)

        findViewById<Button>(R.id.btn_previous).setOnClickListener(this)
        findViewById<Button>(R.id.btn_seek_to_previous).setOnClickListener(this)
        findViewById<Button>(R.id.btn_next).setOnClickListener(this)
        findViewById<Button>(R.id.btn_seek_to_next).setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mExoPlayer.stop()
        mExoPlayer.release()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_mode_off -> {
                mExoPlayer.repeatMode = Player.REPEAT_MODE_OFF
            }
            R.id.btn_mode_one -> {
                mExoPlayer.repeatMode = Player.REPEAT_MODE_ONE
            }
            R.id.btn_mode_all -> {
                mExoPlayer.repeatMode = Player.REPEAT_MODE_ALL
            }
            R.id.btn_seek_back -> {
                mExoPlayer.seekBack()
            }
            R.id.btn_seek_forward -> {
                mExoPlayer.seekForward()
            }
            R.id.btn_previous -> {
                if (mExoPlayer.hasPreviousMediaItem()) {
                    mExoPlayer.seekToPreviousMediaItem()
                }
            }
            R.id.btn_seek_to_previous -> {
                if (mExoPlayer.hasPreviousMediaItem()) {
                    mExoPlayer.seekToPrevious()
                }
            }
            R.id.btn_next -> {
                if (mExoPlayer.hasNextMediaItem()) {
                    mExoPlayer.seekToNextMediaItem()
                }
            }
            R.id.btn_seek_to_next -> {
                if (mExoPlayer.hasNextMediaItem()) {
                    mExoPlayer.seekToNext()
                }
            }
        }
    }

}