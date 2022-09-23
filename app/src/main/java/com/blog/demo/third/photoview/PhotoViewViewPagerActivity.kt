package com.blog.demo.third.photoview

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.blog.demo.R
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewViewPagerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_third_photo_view_pager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = PhotoViewPageAdapter(this)
    }

    class PhotoViewPageAdapter(context: Context) : PagerAdapter() {
        private val mViewList: MutableList<PhotoView> = ArrayList()

        init {
            var drawableArray = arrayOf(R.drawable.switcher1,
                R.drawable.switcher2, R.drawable.switcher3,
                R.drawable.switcher4, R.drawable.switcher5)
            for (resId in drawableArray) {
                var photoView = PhotoView(context)
                photoView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT)
                photoView.setImageResource(resId)
                mViewList.add(photoView)
            }
        }

        // 返回界面数量
        override fun getCount(): Int {
            return mViewList.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mViewList[position])
            return mViewList[position]
        }

        // 去除页面
        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(mViewList[position])
        }

    }

}