package com.blog.demo.control.viewpager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.blog.demo.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdvertisementActivity extends Activity {
    private static final int MOVE_TO_NEXT = 1;

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MOVE_TO_NEXT) {
                int item = mViewPager.getCurrentItem();
                if (++item >= mAdapter.getCount()) {
                    item = 0;
                }
                mViewPager.setCurrentItem(item);
                mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_view_pager);

        mViewPager = findViewById(R.id.view_pager);
        mAdapter = new PicturePageAdapter(this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private boolean mDragging = false;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE && mDragging) {
                    mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
                    mDragging = false;
                } else if (state == ViewPager.SCROLL_STATE_DRAGGING){
                    mHandler.removeMessages(MOVE_TO_NEXT);
                    mDragging = true;
                }
            }
        });
        mHandler.sendEmptyMessageDelayed(MOVE_TO_NEXT, 3000);
    }

    public static class PicturePageAdapter extends PagerAdapter {
        private List<ImageView> mImageViewList = new ArrayList<>();
        private int[] mPictureResource = new int[]{
                R.drawable.switcher1, R.drawable.switcher2, R.drawable.switcher3
        };

        public PicturePageAdapter(Context context) {
            for (int index = 0; index < mPictureResource.length; index++) {
                ImageView iv = new ImageView(context);
                iv.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                iv.setImageResource(mPictureResource[index]);
                iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
                mImageViewList.add(iv);
            }
        }

        // 返回界面数量
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        // 添加界面，一般会添加当前页和左右两边的页面
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        // 去除页面
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position));
        }

    }

}
