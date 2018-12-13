package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/2/14.
 */

public class ViewPagerChangeListenerActivity extends Activity {
    private List<View> mViewList = new ArrayList<>();
    private int[] mDrawableResIdArray = new int[]{
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        for (int resId : mDrawableResIdArray) {
            ImageView view = (ImageView) getLayoutInflater().inflate(
                    R.layout.view_page_item, viewPager, false);
            mViewList.add(view);
            view.setImageResource(resId);
        }

        viewPager.setAdapter(new DemoPageAdapter(mViewList));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 当前页为1时，手势从左往右滑动，position为0，positionOffset从1->0
                // 当前页为1时，手势从右往左滑动，position为1，positionOffset从0->1
                LogUtil.log("ViewPagerActivity", "position = " + position); // 当前页
                LogUtil.log("ViewPagerActivity", "positionOffset = " + positionOffset); // 百分比，为正
                LogUtil.log("ViewPagerActivity", "positionOffsetPixels = " + positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ViewPagerChangeListenerActivity.this, "position = " + position, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.log("ViewPagerActivity", "state = " + getState(state));
            }

            private String getState(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    return "IDLE";
                } else if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    return "DRAGGING";
                } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    return "SETTLING";
                } else {
                    return "unknow state";
                }
            }
        });
    }

}
