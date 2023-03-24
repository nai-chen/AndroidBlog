package com.blog.demo.control

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.blog.demo.LogTool.logi
import com.blog.demo.R

class ClipChildrenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_clip_chilren)

        var viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = PicturePageAdapter(this)

        viewPager = findViewById(R.id.view_pager_clip_pager)
        viewPager.adapter = PicturePageAdapter(this)
        viewPager.pageMargin = resources.getDimensionPixelOffset(R.dimen.margin_dpi_10)
        viewPager.offscreenPageLimit = 2

        findViewById<View>(R.id.view_clip_pager).setOnTouchListener { _, event ->
            viewPager.dispatchTouchEvent(event)
            true
        }
    }

    class PicturePageAdapter(context: Context?) : PagerAdapter() {
        private val mImageViewList: MutableList<ImageView> = ArrayList()
        private val mPictureResource = intArrayOf(
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3,
            R.drawable.switcher4,
            R.drawable.switcher5
        )


        init {
            for (index in mPictureResource.indices) {
                val iv = ImageView(context)
                iv.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
                )
                iv.setImageResource(mPictureResource[index])
                iv.scaleType = ImageView.ScaleType.FIT_CENTER
                mImageViewList.add(iv)
            }
        }

        // 返回界面数量
        override fun getCount(): Int {
            return mImageViewList.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mImageViewList[position])
            logi("ClipChildrenActivity", "instantiateItem position = $position")
            return mImageViewList[position]
        }

        // 去除页面
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(mImageViewList[position])
            logi("ClipChildrenActivity", "destroyItem position = $position")
        }

    }

}