package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.demo.LogUtil;
import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cn on 2017/11/21.
 */

public class ViewPagerAdvertisementActivity extends Activity {
    private final static int MOVE_TO_NEXT = 1;

    private List<View> mViewList = new ArrayList<>();
    private ViewPager mViewPager;
    private int[] mDrawableResIdArray = new int[]{
            R.drawable.switcher1,
            R.drawable.switcher2,
            R.drawable.switcher3,
            R.drawable.switcher4,
            R.drawable.switcher5};

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MOVE_TO_NEXT) {
                int item = mViewPager.getCurrentItem();
                if (++item >= mDrawableResIdArray.length) {
                    item = 0;
                }
                mViewPager.setCurrentItem(item);
                mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        for (int resId : mDrawableResIdArray) {
            ImageView view = (ImageView) getLayoutInflater().inflate(
                    R.layout.view_page_item, mViewPager, false);
            mViewList.add(view);
            view.setImageResource(resId);
        }
        mViewPager.setAdapter(new DemoPageAdapter(mViewList));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                LogUtil.log("ViewPagerAdvertisementActivity", "state = " + state);
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
                } else {
                    mHandler.removeMessages(MOVE_TO_NEXT);
                }
            }
        });
        mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
    }

}
