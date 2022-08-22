package com.blog.demo.image.animation

import android.app.Activity
import android.os.Bundle
import android.transition.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.blog.demo.R

class TransitionVisibilityActivity : Activity(), View.OnClickListener {

    private lateinit var mContainer: ViewGroup
    private lateinit var mIvLink: ImageView
    private lateinit var mIvQQ: ImageView
    private lateinit var mIvPyq: ImageView
    private lateinit var mIvWX: ImageView
    private var mChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_animation_transition_visibility)

        findViewById<Button>(R.id.btn_visibility_code).setOnClickListener(this)
        findViewById<Button>(R.id.btn_visibility_xml).setOnClickListener(this)

        mContainer = findViewById(R.id.layout_container)

        mIvLink = findViewById(R.id.iv_link)
        mIvQQ = findViewById(R.id.iv_qq)
        mIvPyq = findViewById(R.id.iv_pyq)
        mIvWX = findViewById(R.id.iv_wx)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_visibility_code) {
            TransitionManager.beginDelayedTransition(mContainer,
                TransitionSet()
                    .addTransition(Fade().addTarget(R.id.iv_link))
                    .addTransition(Slide(Gravity.TOP).addTarget(R.id.iv_qq))
                    .addTransition(Explode().addTarget(R.id.iv_pyq).addTarget(R.id.iv_wx))
            )
            if (!mChange) {
                mIvLink.visibility = View.GONE
                mIvQQ.visibility = View.GONE
                mIvPyq.visibility = View.GONE
                mIvWX.visibility = View.GONE
            } else {
                mIvLink.visibility = View.VISIBLE
                mIvQQ.visibility = View.VISIBLE
                mIvPyq.visibility = View.VISIBLE
                mIvWX.visibility = View.VISIBLE
            }
            mChange = !mChange
        } else if (v.id == R.id.btn_visibility_xml) {
            val transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.transition_visibility)
            TransitionManager.beginDelayedTransition(mContainer, transition)
            if (!mChange) {
                mIvLink.visibility = View.GONE
                mIvQQ.visibility = View.GONE
                mIvPyq.visibility = View.GONE
                mIvWX.visibility = View.GONE
            } else {
                mIvLink.visibility = View.VISIBLE
                mIvQQ.visibility = View.VISIBLE
                mIvPyq.visibility = View.VISIBLE
                mIvWX.visibility = View.VISIBLE
            }
            mChange = !mChange
        }
    }

}