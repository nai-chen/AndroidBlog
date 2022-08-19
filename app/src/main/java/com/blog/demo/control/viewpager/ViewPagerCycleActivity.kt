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
import com.blog.demo.R

class ViewPagerCycleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_control_view_pager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        val adapter: PagerAdapter = CyclePageAdapter(this)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
            }

            override fun onPageScrollStateChanged(state: Int) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    val position = viewPager.currentItem
                    if (position == 0) {
                        // 第一个界面，切换到原来的最后一个界面
                        viewPager.setCurrentItem(adapter.count - 2, false)
                    } else if (position == adapter.count - 1) {
                        // 最后一个界面，切换到原来的第一个界面
                        viewPager.setCurrentItem(1, false)
                    }
                }
            }
        })
        // 初始页为1
        viewPager.currentItem = 1
    }

    class CyclePageAdapter(context: Context?) : PagerAdapter() {
        private val mViewList: MutableList<View> = ArrayList()

        // 返回界面数量+2
        override fun getCount(): Int {
            return mViewList.size + 2
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View
            view = if (position == 0) {
                // 第一个界面，选择原来的最后一个界面
                mViewList[mViewList.size - 1]
            } else if (position == mViewList.size + 1) {
                // 最后一个界面，选择原来的第一个界面
                mViewList[0]
            } else {
                // 原有的依次从0开始
                mViewList[position - 1]
            }
            // 页面如果重复添加，会发生异常
            try {
                container.addView(view)
            } catch (e: Exception) {
            }
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            // 不在删除页面
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