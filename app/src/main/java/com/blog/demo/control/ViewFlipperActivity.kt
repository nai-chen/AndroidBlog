package com.blog.demo.control

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ViewFlipper
import com.blog.demo.R

class ViewFlipperActivity : Activity() {

    private lateinit var mViewFlipper: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_flipper)
        mViewFlipper = findViewById(R.id.view_flipper)

        findViewById<Button>(R.id.btn_show_previous).setOnClickListener {
            previous()
        }
        findViewById<Button>(R.id.btn_play).setOnClickListener {
            play()
        }
        findViewById<Button>(R.id.btn_show_next).setOnClickListener {
            next()
        }
    }

    // 显示上一个视图
    private fun previous() {
        mViewFlipper.stopFlipping()
        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_top)
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_bottom)
        mViewFlipper.showPrevious()
    }

    // 手动开始轮播
    private fun play() {
        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom)
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top)
        mViewFlipper.startFlipping()
    }

    // 显示下一个视图
    private operator fun next() {
        mViewFlipper.stopFlipping()
        mViewFlipper.setInAnimation(this, R.anim.anim_enter_from_bottom)
        mViewFlipper.setOutAnimation(this, R.anim.anim_exit_to_top)
        mViewFlipper.showNext()
    }

}