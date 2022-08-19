package com.blog.demo.control.viewpager

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.blog.demo.LogTool
import com.blog.demo.R

class ViewPagerTransformerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_pager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = PicturePageAdapter(this)
        viewPager.setPageTransformer(true, PicturePageTransformer())
    }

    class PicturePageAdapter(context: Context?) : PagerAdapter() {
        private val mImageViewList: MutableList<ImageView> = ArrayList()
        private val mPictureResource = intArrayOf(
            R.drawable.switcher1, R.drawable.switcher2, R.drawable.switcher3
        )

        // 返回界面数量
        override fun getCount(): Int {
            return mImageViewList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mImageViewList[position])
            return mImageViewList[position]
        }

        // 去除页面
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(mImageViewList[position])
        }

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
    }

    private class PicturePageTransformer : ViewPager.PageTransformer {
        override fun transformPage(page: View, position: Float) {
            LogTool.logi("ViewPagerTransformerActivity", "${page.toString()} position = $position")
            if (position < -1) {
                page.alpha = 0f
            } else if (position <= 0) {
                // 左右移动，并且移除时变透明
                page.alpha = 1 + position
            } else if (position < 1) {
                // 去除左右移动效果
                page.translationX = -page.width * position
                // 进入时变大，移除时变小
                page.scaleX = 1 - position / 2
                page.scaleY = 1 - position / 2
                page.alpha = 1 - position
            } else {
                page.alpha = 0f
            }
        }
    }

}