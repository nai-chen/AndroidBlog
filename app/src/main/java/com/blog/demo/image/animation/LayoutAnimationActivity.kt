package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Button
import com.blog.demo.R

class LayoutAnimationActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_layout)

        findViewById<Button>(R.id.btn_start_animation).setOnClickListener(this)
        findViewById<Button>(R.id.btn_custom_layout_control).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_start_animation) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_from_right)
            val controller = LayoutAnimationController(animation)
            controller.delay = 0.1f
            controller.order = LayoutAnimationController.ORDER_NORMAL

            val container = findViewById<ViewGroup>(R.id.container)
            container.layoutAnimation = controller
            container.startLayoutAnimation()
        } else if (v.id == R.id.btn_custom_layout_control) {
            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_in_from_right)
            val controller: LayoutAnimationController = CustomLayoutAnimationController(animation)
            controller.delay = 0.1f
            controller.order = -1

            val container = findViewById<ViewGroup>(R.id.container)
            container.layoutAnimation = controller
            container.startLayoutAnimation()
        }
    }

    private class CustomLayoutAnimationController constructor(animation: Animation) : LayoutAnimationController(animation) {

        override fun getTransformedIndex(params: AnimationParameters): Int {
            return if (order < 0) {
                val index = params.index
                val count = (params.count + 1) / 2
                if (index < count) {
                    count - 1 - params.index
                } else {
                    params.index - count
                }
            } else {
                super.getTransformedIndex(params)
            }
        }

    }

}