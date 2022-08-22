package com.blog.demo.image.animation

import android.app.Activity
import android.graphics.Rect
import android.os.Bundle
import android.transition.ChangeClipBounds
import android.transition.ChangeImageTransform
import android.transition.ChangeTransform
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R

class TransitionAnimationDelayActivity : Activity(), View.OnClickListener {

    private lateinit var mContainer: ViewGroup
    private lateinit var mImageView: ImageView
    private var mChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_transition_delay)

        mContainer = findViewById(R.id.layout_container)
        mImageView = findViewById(R.id.image_view)

        findViewById<Button>(R.id.btn_change_transform).setOnClickListener(this)
        findViewById<Button>(R.id.btn_change_clip_bounds).setOnClickListener(this)
        findViewById<Button>(R.id.btn_change_image_transform).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_change_transform) {
            TransitionManager.beginDelayedTransition(mContainer, ChangeTransform())
            if (mChange) {
                mImageView.scaleX = 1f
                mImageView.scaleY = 1f
            } else {
                mImageView.scaleX = 0.5f
                mImageView.scaleY = 0.5f
            }
            mChange = !mChange
        } else if (v.id == R.id.btn_change_clip_bounds) {
            TransitionManager.beginDelayedTransition(mContainer, ChangeClipBounds())
            if (mChange) {
                mImageView.clipBounds = null
            } else {
                mImageView.clipBounds = Rect(10, 10, 110, 110)
            }
            mChange = !mChange
        } else if (v.id == R.id.btn_change_image_transform) {
            TransitionManager.beginDelayedTransition(mContainer, ChangeImageTransform())
            if (mChange) {
                mImageView.scaleType = ImageView.ScaleType.FIT_CENTER
            } else {
                mImageView.scaleType = ImageView.ScaleType.CENTER
            }
            mChange = !mChange
        }
    }
}