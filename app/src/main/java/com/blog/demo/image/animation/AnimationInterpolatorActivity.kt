package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R

class AnimationInterpolatorActivity : Activity(), View.OnClickListener {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_interpolator)

        findViewById<Button>(R.id.btn_accelerate_decelerate).setOnClickListener(this)
        findViewById<Button>(R.id.btn_accelerate).setOnClickListener(this)
        findViewById<Button>(R.id.btn_anticipate).setOnClickListener(this)
        findViewById<Button>(R.id.btn_anticipate_overshoot).setOnClickListener(this)
        findViewById<Button>(R.id.btn_bounce).setOnClickListener(this)
        findViewById<Button>(R.id.btn_cycle).setOnClickListener(this)
        findViewById<Button>(R.id.btn_decelerate).setOnClickListener(this)
        findViewById<Button>(R.id.btn_linear).setOnClickListener(this)
        findViewById<Button>(R.id.btn_overshoot).setOnClickListener(this)

        imageView = findViewById(R.id.image_view)
    }

    override fun onClick(v: View) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate)
        if (v.id == R.id.btn_accelerate_decelerate) {
            animation.interpolator = AccelerateDecelerateInterpolator()
        } else if (v.id == R.id.btn_accelerate) {
            animation.interpolator = AccelerateInterpolator()
        } else if (v.id == R.id.btn_anticipate) {
            animation.interpolator = AnticipateInterpolator()
        } else if (v.id == R.id.btn_anticipate_overshoot) {
            animation.interpolator = AnticipateOvershootInterpolator()
        } else if (v.id == R.id.btn_bounce) {
            animation.interpolator = BounceInterpolator()
        } else if (v.id == R.id.btn_cycle) {
            animation.interpolator = CycleInterpolator(4f)
        } else if (v.id == R.id.btn_decelerate) {
            animation.interpolator = DecelerateInterpolator()
        } else if (v.id == R.id.btn_linear) {
            animation.interpolator = LinearInterpolator()
        } else if (v.id == R.id.btn_overshoot) {
            animation.interpolator = OvershootInterpolator()
        }
        imageView.startAnimation(animation)
    }

}