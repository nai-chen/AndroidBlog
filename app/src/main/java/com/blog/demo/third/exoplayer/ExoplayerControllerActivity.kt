package com.blog.demo.third.exoplayer

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.blog.demo.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

class ExoplayerControllerActivity : Activity(), View.OnClickListener {

    private lateinit var mExoPlayer: ExoPlayer
    private lateinit var mPlayerView: StyledPlayerView
    private var mShowRewind: Boolean = true
    private var mShowFastForward: Boolean = true
    private var mShowPrevious: Boolean = true
    private var mShowNext: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_exoplayer_controller)

        mPlayerView = findViewById(R.id.styled_player_view)
        mExoPlayer = ExoPlayer.Builder(this).build()
        mPlayerView.player = mExoPlayer

        var uri = "android.resource://com.blog.demo/${R.raw.video_demo}"
        mExoPlayer.setMediaItem(MediaItem.fromUri(Uri.parse(uri)))
        var nextUri = "android.resource://com.blog.demo/${R.raw.video_demo2}"
        mExoPlayer.addMediaItem(MediaItem.fromUri(Uri.parse(nextUri)))
        mExoPlayer.prepare()
        mExoPlayer.play()

        findViewById<Button>(R.id.btn_controller_hide_on_touch).setOnClickListener(this)
        findViewById<Button>(R.id.btn_controller_auto_show).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_timeout).setOnClickListener(this)

        findViewById<Button>(R.id.btn_show_rewind).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_fast_forward).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_previous).setOnClickListener(this)
        findViewById<Button>(R.id.btn_show_next).setOnClickListener(this)

        mPlayerView.setControllerVisibilityListener {
            if (it == View.VISIBLE) {
                Log.i("ExoplayerController", "ControllerVisibility is Visible")
            } else if (it == View.GONE) {
                Log.i("ExoplayerController", "ControllerVisibility is Gone")
            }
        }
        mPlayerView.setAspectRatioListener { targetAspectRatio, naturalAspectRatio, aspectRatioMismatch ->
//            Log.i("ExoplayerController", "targetAspectRatio is $targetAspectRatio")
//            Log.i("ExoplayerController", "naturalAspectRatio is $naturalAspectRatio")
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_controller_hide_on_touch -> {
                mPlayerView.controllerHideOnTouch = !mPlayerView.controllerHideOnTouch
                Log.i("ExoplayerController", "mPlayerView.controllerHideOnTouch = ${mPlayerView.controllerHideOnTouch}")
            }
            R.id.btn_controller_auto_show -> {
                mPlayerView.controllerAutoShow = !mPlayerView.controllerAutoShow
                Log.i("ExoplayerController", "mPlayerView.controllerAutoShow = ${mPlayerView.controllerAutoShow}")
            }
            R.id.btn_show_timeout -> {
                Log.i("ExoplayerController", "mPlayerView.controllerShowTimeoutMs = ${mPlayerView.controllerShowTimeoutMs}")
                mPlayerView.controllerShowTimeoutMs = 10000
            }
            R.id.btn_show_rewind -> {
                mShowRewind = !mShowRewind
                Log.i("ExoplayerController", "mPlayerView.showRewind = $mShowRewind")
                mPlayerView.setShowRewindButton(mShowRewind)
            }
            R.id.btn_show_fast_forward -> {
                mShowFastForward = !mShowFastForward
                Log.i("ExoplayerController", "mPlayerView.showFastForward = $mShowFastForward")
                mPlayerView.setShowFastForwardButton(mShowFastForward)
            }
            R.id.btn_show_previous -> {
                mShowPrevious = !mShowPrevious
                Log.i("ExoplayerController", "mPlayerView.showPrevious = $mShowPrevious")
                mPlayerView.setShowPreviousButton(mShowPrevious)
            }
            R.id.btn_show_next -> {
                mShowNext = !mShowNext
                Log.i("ExoplayerController", "mPlayerView.controllerShowTimeoutMs = $mShowNext")
                mPlayerView.setShowNextButton(mShowNext)
            }
        }
    }

}