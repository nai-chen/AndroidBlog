package com.blog.demo.image.animation

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import com.blog.demo.R

class FrameAnimationActivity : Activity(), View.OnClickListener {

    private lateinit var mDrawable: AnimationDrawable
    private lateinit var cbOneShot: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_frame)

        findViewById<Button>(R.id.btn_start).setOnClickListener(this)
        findViewById<Button>(R.id.btn_stop).setOnClickListener(this)

        cbOneShot = findViewById(R.id.checkbox)

        val imageView: ImageView = findViewById(R.id.iv_frame)
        mDrawable = imageView.drawable as AnimationDrawable
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start) {
            mDrawable.isOneShot = cbOneShot.isChecked
            mDrawable.start()
        } else if (v.id == R.id.btn_stop) {
            mDrawable.stop()
        }
    }

}