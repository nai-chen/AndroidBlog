package com.blog.demo.control.viewpager

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.blog.demo.LogTool
import com.blog.demo.R

class ViewPagerAdapterActivity : Activity() {
    private val LOG_TAG = "ViewPagerAdapterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_pager)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = DemoPageAdapter(this)

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                LogTool.logi(LOG_TAG, "position = " + position
                            + ", positionOffset = " + positionOffset
                            + ", positionOffsetPixels = " + positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                LogTool.logi(LOG_TAG, "position = $position")
            }

            override fun onPageScrollStateChanged(state: Int) {
                LogTool.logi(LOG_TAG, "state = $state")
            }
        })
    }

    class DemoPageAdapter(context: Context) : PagerAdapter() {
        private val mViewList: MutableList<View> = ArrayList()

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

        init {
            for (index in 0..3) {
                val view = View.inflate(context, R.layout.view_pager_text, null) as TextView
                view.text = "第" + index + "页"
                mViewList.add(view)
            }
        }
    }
}