package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R

class TweenAnimationActivity : Activity(), View.OnClickListener {
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_tween)

        findViewById<Button>(R.id.btn_translate_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_translate_code).setOnClickListener(this)

        findViewById<Button>(R.id.btn_scale_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_scale_code).setOnClickListener(this)

        findViewById<Button>(R.id.btn_rotate_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_rotate_code).setOnClickListener(this)

        findViewById<Button>(R.id.btn_alpha_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_alpha_code).setOnClickListener(this)

        findViewById<Button>(R.id.btn_set_xml).setOnClickListener(this)
        findViewById<Button>(R.id.btn_set_code).setOnClickListener(this)

        findViewById<Button>(R.id.btn_cancel).setOnClickListener(this)

        mImageView = findViewById(R.id.image_view)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_translate_xml) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate)
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_translate_code) {
            val animation: Animation = TranslateAnimation(
                0, 0f, Animation.RELATIVE_TO_SELF, 1.0f,
                0, 0f, 0, 0f)
            animation.duration = 3000
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_scale_xml) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale)
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_scale_code) {
            val animation: Animation = ScaleAnimation(
                1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f
            )
            animation.duration = 3000
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_rotate_xml) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate)
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_rotate_code) {
            val animation: Animation = RotateAnimation(
                0f, 360f,  // -360逆时针
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f    )
            animation.duration = 3000
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_alpha_xml) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_alpha)
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_alpha_code) {
            val animation: Animation = AlphaAnimation(1.0f, 0f)
            animation.duration = 3000
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_set_xml) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_set)
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_set_code) {
            val animation = AnimationSet(true)
            val alphaAnim: Animation = AlphaAnimation(0f, 1.0f)
            animation.addAnimation(alphaAnim)
            val scaleAnim: Animation = ScaleAnimation(
                0.5f, 1.0f, 0.5f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            animation.addAnimation(scaleAnim)
            animation.duration = 3000
            mImageView.startAnimation(animation)
        } else if (v.id == R.id.btn_cancel) {
            mImageView.clearAnimation()
        }
    }


}