package com.blog.demo.control.viewpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/11/22.
 */

public class DemoPageAdapter extends PagerAdapter {
    private List<View> mList = new ArrayList<>();

    public DemoPageAdapter(List<View> list) {
        this.mList = list;
    }

    // 返回界面数量
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // 添加界面，一般会添加当前页和左右两边的页面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    // 去除页面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

}