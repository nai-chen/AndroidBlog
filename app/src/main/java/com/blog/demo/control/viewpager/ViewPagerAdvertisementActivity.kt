package com.blog.demo.control.viewpager

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.blog.demo.R

class ViewPagerAdvertisementActivity : Activity() {

    private val MOVE_TO_NEXT = 1

    private lateinit var mViewPager: ViewPager
    private lateinit var mAdapter: PagerAdapter

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == MOVE_TO_NEXT) {
                var item = mViewPager.currentItem
                if (++item >= mAdapter.count) {
                    item = 0
                }
                mViewPager.currentItem = item
                sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_pager)
        mViewPager = findViewById(R.id.view_pager)
        mAdapter = PicturePageAdapter(this)
        mViewPager.adapter = mAdapter

        mViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            private var mDragging = false
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE && mDragging) {
                    mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000)
                    mDragging = false
                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    mHandler.removeMessages(MOVE_TO_NEXT)
                    mDragging = true
                }
            }
        })
        mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000)
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

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            container.addView(mImageViewList[position])
            return mImageViewList[position]
        }

        // 去除页面
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
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

}