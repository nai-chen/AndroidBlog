package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerCycleActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_pager);

        final ViewPager viewPager = findViewById(R.id.view_pager);
        final PagerAdapter adapter = new CyclePageAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int position = viewPager.getCurrentItem();
                    if (position == 0) {
                        // 第一个界面，切换到原来的最后一个界面
                        viewPager.setCurrentItem(adapter.getCount() - 2, false);
                    } else if (position == adapter.getCount() - 1) {
                        // 最后一个界面，切换到原来的第一个界面
                        viewPager.setCurrentItem(1, false);
                    }
                }
            }
        });
        // 初始页为1
        viewPager.setCurrentItem(1);
    }

    public static class CyclePageAdapter extends PagerAdapter {
        private List<View> mViewList = new ArrayList<>();

        public CyclePageAdapter(Context context) {
            for (int index = 0; index < 4; index++) {
                TextView view = (TextView) View.inflate(context, R.layout.view_pager_text, null);
                view.setText("第" + index + "页");
                mViewList.add(view);
            }
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

    }

}
