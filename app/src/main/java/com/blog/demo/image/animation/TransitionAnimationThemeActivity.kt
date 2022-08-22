package com.blog.demo.image.animation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.ImageView
import com.blog.demo.R

class TransitionAnimationThemeActivity : TransitionAnimationActivity() {

    private lateinit var mIvLink: ImageView
    private lateinit var mIvQQ: ImageView
    private lateinit var mIvPyq: ImageView
    private lateinit var mIvWx: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mIvLink = findViewById(R.id.iv_link)
        mIvLink.setOnClickListener(this)

        mIvQQ = findViewById(R.id.iv_qq)
        mIvQQ.setOnClickListener(this)

        mIvPyq = findViewById(R.id.iv_pyq)
        mIvPyq.setOnClickListener(this)

        mIvWx = findViewById(R.id.iv_wx)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.iv_link) {
            val intent = Intent(this, TransitionAnimationThemeResultActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            startActivity(intent, bundle)
        } else if (view.id == R.id.iv_qq) {
            val intent = Intent(this, TransitionAnimationThemeResultActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this, mIvQQ, "sharedView2").toBundle()
            startActivity(intent, bundle)
        } else if (view.id == R.id.iv_pyq) {
            val intent = Intent(this, TransitionAnimationThemeResultActivity::class.java)
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this,
                Pair.create(mIvLink, "sharedView1"),
                Pair.create(mIvQQ, "sharedView2"),
                Pair.create(mIvPyq, "sharedView3"),
                Pair.create(mIvWx, "sharedView4")
            ).toBundle()
            startActivity(intent, bundle)
        }
    }
}