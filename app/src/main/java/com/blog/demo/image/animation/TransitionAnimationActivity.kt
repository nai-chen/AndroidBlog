package com.blog.demo.image.animation

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import com.blog.demo.R

open class TransitionAnimationActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_transition)
        findViewById<ImageView>(R.id.iv_link).setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.iv_link) {
            val intent = Intent(this, TransitionAnimationResultActivity::class.java)
            window.exitTransition = TransitionSet()
                .addTransition(Slide(Gravity.LEFT).addTarget(R.id.iv_link).addTarget(R.id.iv_pyq))
                .addTransition(Slide(Gravity.RIGHT).addTarget(R.id.iv_qq).addTarget(R.id.iv_wx))
                .setDuration(1000)

            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            startActivity(intent, bundle)
        }
    }

}