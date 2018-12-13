package com.blog.demo.control.viewpager;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/11/22.
 */

public class CyclePageAdapter extends PagerAdapter {
    private ViewPager mViewPager;
    private List<View> mViewList = new ArrayList<>();

    public CyclePageAdapter(ViewPager viewPager, List<View> list) {
        this.mViewPager = viewPager;
        this.mViewList = list;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    // 第一个界面，切换到原来的最后一个界面
                    mViewPager.setCurrentItem(mViewList.size(), false);
                } else if (position == mViewList.size() + 1) {
                    // 最后一个界面，切换到原来的第一个界面
                    mViewPager.setCurrentItem(1, false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    // 返回界面数量+2
    @Override
    public int getCount() {
        return mViewList.size() + 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view;
        if (position == 0) {
            // 第一个界面，选择原来的最后一个界面
            view = mViewList.get(mViewList.size() - 1);
        } else if (position == mViewList.size() + 1) {
            // 最后一个界面，选择原来的第一个界面
            view = mViewList.get(0);
        } else {
            // 原有的依次从0开始
            view = mViewList.get(position - 1);
        }
        // 页面如果重复添加，会发生异常
        try {
            container.addView(view);
        } catch (Exception e) {
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 不在删除页面
    }

    public void setCurrentItem(int position) {
        mViewPager.setCurrentItem(position + 1);
    }

}