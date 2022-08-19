package com.blog.demo.control.viewswitcher

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ViewSwitcher
import com.blog.demo.R

class ViewSwitcherFactoryActivity : Activity() {
    private lateinit var mViewSwitcher: ViewSwitcher
    private var mPosition = 0
    private val mResIdArray = intArrayOf(
        R.drawable.switcher1,
        R.drawable.switcher2,
        R.drawable.switcher3,
        R.drawable.switcher4,
        R.drawable.switcher5
    )
    private val mCount = mResIdArray.size

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_switcher_factory)

        mViewSwitcher = findViewById(R.id.view_switcher)
        mViewSwitcher.setFactory {
            val iv = ImageView(this@ViewSwitcherFactoryActivity)
            iv.scaleType = ImageView.ScaleType.FIT_XY
            iv.layoutParams = FrameLayout.LayoutParams(-1, -1)
            iv
        }

        (mViewSwitcher.currentView as ImageView).setImageResource(R.drawable.switcher1)

        findViewById<Button>(R.id.btn_show_previous).setOnClickListener {
            previous()
        }
        findViewById<Button>(R.id.btn_show_next).setOnClickListener {
            next()
        }
    }

    private operator fun next() {
        ++mPosition
        setSelection(mViewSwitcher.nextView as ImageView)
        mViewSwitcher.setInAnimation(this, R.anim.anim_enter_from_bottom)
        mViewSwitcher.setOutAnimation(this, R.anim.anim_exit_to_top)
        mViewSwitcher.showNext()
    }

    private fun previous() {
        --mPosition
        setSelection(mViewSwitcher.nextView as ImageView)
        mViewSwitcher.setInAnimation(this, R.anim.anim_enter_from_top)
        mViewSwitcher.setOutAnimation(this, R.anim.anim_exit_to_bottom)
        mViewSwitcher.showPrevious()
    }

    private fun setSelection(imageView: ImageView) {
        if (mPosition < 0) {
            mPosition = mCount - 1
        } else if (mPosition >= mCount) {
            mPosition = 0
        }
        imageView.setImageResource(mResIdArray[mPosition])
    }

}