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

import com.blog.demo.LogTool;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterActivity extends Activity {
    private static final String LOG_TAG = "ViewPagerAdapterActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_pager);

        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new DemoPageAdapter(this));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                       int positionOffsetPixels) {
                LogTool.logi(LOG_TAG, "position = " + position
                        + ", positionOffset = " + positionOffset
                        + ", positionOffsetPixels = " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                LogTool.logi(LOG_TAG, "position = " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogTool.logi(LOG_TAG, "state = " + state);
            }
        });
    }

    public static class DemoPageAdapter extends PagerAdapter {
        private List<View> mViewList = new ArrayList<>();

        public DemoPageAdapter(Context context) {
            for (int index = 0; index < 4; index++) {
                TextView view = (TextView) View.inflate(context, R.layout.view_pager_text, null);
                view.setText("第" + index + "页");
                mViewList.add(view);
            }
        }

        // 返回界面数量
        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        // 去除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));
        }

    }

}
