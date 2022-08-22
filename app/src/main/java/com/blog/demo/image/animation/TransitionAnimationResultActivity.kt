package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.transition.Explode
import android.transition.Fade
import android.transition.TransitionSet
import com.blog.demo.R

class TransitionAnimationResultActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_transition_result)

        window.enterTransition = TransitionSet()
            .addTransition(Fade().addTarget(R.id.iv_link).addTarget(R.id.iv_qq))
            .addTransition(Explode().addTarget(R.id.iv_pyq).addTarget(R.id.iv_wx))
            .setDuration(1000)
    }

}