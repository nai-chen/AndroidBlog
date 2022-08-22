package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Scene
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.blog.demo.R

class TransitionAnimationGoActivity : Activity(), View.OnClickListener {

    private lateinit var mContainer: ViewGroup
    private var mChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_image_animation_transition_go)

        findViewById<Button>(R.id.btn_go).setOnClickListener(this)
        mContainer = findViewById(R.id.layout_container)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_go) {
            go()
        }
    }

    private fun go() {
        if (mChange) {
            val scene = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_init, this)
            TransitionManager.go(scene, ChangeBounds())
        } else {
            val scene = Scene.getSceneForLayout(mContainer, R.layout.layout_scene_change_bounds, this)
            TransitionManager.go(scene, ChangeBounds())
        }
        mChange = !mChange
    }
}